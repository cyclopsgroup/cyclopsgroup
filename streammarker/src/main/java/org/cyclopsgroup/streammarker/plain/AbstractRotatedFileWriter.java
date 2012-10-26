package org.cyclopsgroup.streammarker.plain;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Mark;

public abstract class AbstractRotatedFileWriter
    implements StreamMarksWriter
{
    private class Output
        implements Closeable
    {
        private final File file;

        private long lastWrite;

        private PrintWriter output;

        private long writes;

        private Output( File file )
        {
            this.file = file;
        }

        public synchronized void close()
        {
            if ( output == null )
            {
                return;
            }
            output.flush();
            output.close();
            output = null;

            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Output is closed for file " + file );
            }
        }

        private synchronized void flush()
        {
            if ( output != null )
            {
                if ( LOG.isDebugEnabled() )
                {
                    LOG.debug( "Flush writes to file " + file );
                }
                output.flush();
            }
        }

        private synchronized boolean isOpen()
        {
            return output != null;
        }

        private synchronized void open()
            throws IOException
        {
            if ( output != null )
            {
                throw new IllegalStateException( "Output to " + file + " is already opened" );
            }
            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Output is opened for file " + file );
            }
            output = new PrintWriter( new FileOutputStream( file, true ) );
        }

        private synchronized void writeBody( String body )
            throws IOException
        {
            writeLine( String.format( "B:{N:%011d,C:{%s}}", writes++, body ) );
            lastWrite = System.currentTimeMillis();
        }

        private void writeHeader()
        {
            writeLine( String.format( "H:{I\"%s\",L:\"%s\",A:\"%s\",S:%d}", application.getLocationIdentifier(),
                                      application.getLocationName(), application.getApplicationName(),
                                      System.currentTimeMillis() ) );
        }

        private synchronized void writeLine( String line )
        {
            if ( output == null )
            {
                throw new IllegalStateException( "File " + file + " is not opened yet" );
            }
            output.println( line );
        }
    }

    private static final long DEFAULT_FLUSH_IN_MILLIS = 1000L;

    private static final long DEFAULT_MAX_IDLE_MILLIS = 30 * 1000L;

    private static final Log LOG = LogFactory.getLog( AbstractRotatedFileWriter.class );

    private final Application application;

    private final File directory;

    private long flushInMillis = DEFAULT_FLUSH_IN_MILLIS;

    private long maxIdleMillis = DEFAULT_MAX_IDLE_MILLIS;

    private Output output;

    private boolean terminated;

    protected AbstractRotatedFileWriter( Application application, ScheduledExecutorService scheduler,
                                         long intervalMillis, File directory )
    {
        Validate.notNull( application, "Application can't be NULL" );
        Validate.notNull( scheduler, "Scheduler can't be NULL" );
        Validate.isTrue( directory.isDirectory(), "Directory " + directory + " is not valid" );
        this.application = application;
        this.directory = directory;
        scheduler.scheduleWithFixedDelay( new Runnable()
        {
            public void run()
            {
                checkOutput();
            }
        }, intervalMillis, intervalMillis, TimeUnit.MILLISECONDS );
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            public void run()
            {
                AbstractRotatedFileWriter.this.close();
            }
        } );
    }

    protected abstract String calculateFileName();

    private synchronized void checkOutput()
    {

        if ( terminated || output == null || !output.isOpen() || output.lastWrite == 0 )
        {
            return;
        }
        long now = System.currentTimeMillis();
        try
        {
            if ( now >= output.lastWrite + flushInMillis )
            {
                output.flush();
                rotateFileIfNecessary();
            }
            if ( output.lastWrite + maxIdleMillis < System.currentTimeMillis() )
            {
                output.close();
            }
        }
        catch ( IOException e )
        {
            LOG.error( "Can't rotate file", e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public synchronized void close()
    {
        terminated = true;
        if ( output != null && output.isOpen() )
        {
            output.close();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void finalize()
    {
        close();
    }

    private synchronized void rotateFileIfNecessary()
        throws IOException
    {
        String fileName = calculateFileName();
        // If a different file is required, close current file first
        if ( output != null && !output.file.getName().equals( fileName ) )
        {
            output.close();
            output = null;
        }

        // If there isn't an output available, create one
        if ( output == null )
        {
            output = new Output( new File( directory, fileName ) );
            output.open();
            output.writeHeader();
        }
        else if ( !output.isOpen() )
        {
            output.open();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public synchronized void write( String bucket, long timestamp, Iterable<Mark> marks )
        throws IOException
    {
        // If there isn't an output available, create one
        if ( output == null || !output.isOpen() )
        {
            rotateFileIfNecessary();
        }
        StringBuilder s =
            new StringBuilder( "B:\"" ).append( bucket ).append( "\",T:" ).append( timestamp ).append( ",M:[" );
        boolean start = true;
        for ( Mark mark : marks )
        {
            if ( start )
            {
                start = false;
            }
            else
            {
                s.append( "," );
            }
            s.append( String.format( "{N:\"%s\",K:\"%s\",V:%d,T:[\"%s\"]}", mark.getName(), mark.getKind(),
                                     mark.getValue(), StringUtils.join( mark.getTags(), "\",\"" ) ) );
        }
        s.append( "]" );
        output.writeBody( s.toString() );
    }
}
