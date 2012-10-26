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

        private boolean started;

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

        private synchronized boolean isOpen()
        {
            return output != null;
        }

        private synchronized void open()
            throws IOException
        {
            if ( output != null )
            {
                throw new IllegalStateException( "File " + file + " is already opened" );
            }
            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Output is opened for file " + file );
            }
            output = new PrintWriter( new FileOutputStream( file, true ) );
        }

        private synchronized void writeLine( String line )
            throws IOException
        {
            if ( output == null )
            {
                throw new IllegalStateException( "File " + file + " is not opened yet" );
            }

            if ( !started )
            {
                // FIXME Write header
                started = true;
            }

            output.printf( "B:{N:%011d,C:{%s}}", writes++, line );
            output.println();

            // TODO Optimize the flush
            output.flush();
            lastWrite = System.currentTimeMillis();
        }
    }

    private static final Log LOG = LogFactory.getLog( AbstractRotatedFileWriter.class );

    private final Application application;

    private long maxIdleMillis;

    private Output output;

    private boolean terminated;

    private final File directory;

    protected AbstractRotatedFileWriter( Application application, ScheduledExecutorService scheduler,
                                         long intervalMillis, long maxIdleMillis, File directory )
    {
        Validate.notNull( application, "Application can't be NULL" );
        Validate.notNull( scheduler, "Scheduler can't be NULL" );
        Validate.isTrue( directory.isDirectory(), "Directory " + directory + " is not valid" );
        this.application = application;
        this.directory = directory;
        this.maxIdleMillis = maxIdleMillis;
        checkOutput();
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
                AbstractRotatedFileWriter.this.stop();
            }
        } );
    }

    protected abstract String calculateFileName();

    private synchronized void checkOutput()
    {
        if ( terminated )
        {
            return;
        }
        if ( output == null )
        {
            output = new Output( new File( directory, calculateFileName() ) );
        }
        if ( !output.isOpen() )
        {
            return;
        }
        if ( output.lastWrite + maxIdleMillis < System.currentTimeMillis() )
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
        stop();
    }

    private void stop()
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
    public synchronized void write( String bucket, long timestamp, Iterable<Mark> marks )
        throws IOException
    {
        if ( output == null )
        {

        }
        if ( !output.isOpen() )
        {
            output.open();
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
        output.writeLine( s.toString() );
    }
}
