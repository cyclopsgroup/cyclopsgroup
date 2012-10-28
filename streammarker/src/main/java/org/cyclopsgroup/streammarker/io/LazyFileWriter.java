package org.cyclopsgroup.streammarker.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Provider;
import org.cyclopsgroup.streammarker.utils.DaemonThreadFactory;

class LazyFileWriter
    implements Closeable
{
    private static class Session
    {
        private final Writer writer;

        private long lastWrite;

        private long lastFlush;

        private File outputFile;

        private boolean appending;

        private Session( File outputFile )
            throws IOException
        {
            this.outputFile = outputFile;
            this.appending = outputFile.exists();

            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Opening session with file " + outputFile );
            }
            Writer f;
            if ( appending )
            {
                f = new FileWriter( outputFile, true );
            }
            else
            {
                f = new FileWriter( outputFile );
            }
            this.writer = new BufferedWriter( f );
            this.lastFlush = System.currentTimeMillis();
        }
    }

    private static final Log LOG = LogFactory.getLog( LazyFileWriter.class );

    private MarkOutput output;

    private Session session;

    private final Provider<File> outputFileProvider;

    private final ScheduledExecutorService scheduler =
        Executors.newSingleThreadScheduledExecutor( new DaemonThreadFactory( getClass().getSimpleName() + "-"
            + hashCode() ) );

    private long leastFlushInterval = 1000L;

    private long maxIdle = 10 * 1000L;

    private final Application application;

    LazyFileWriter( Application application, Provider<File> outputFileProvider )
    {
        this( application, outputFileProvider, 1000L );
    }

    LazyFileWriter( Application application, Provider<File> outputFileProvider, long checkInterval )
    {
        this.outputFileProvider = outputFileProvider;
        this.application = application;
        this.output = new TextWriterMarkOutput( new Provider<Writer>()
        {
            public Writer provide()
            {
                return LazyFileWriter.this.getWriter();
            }
        } );
        scheduler.scheduleWithFixedDelay( new Runnable()
        {
            public void run()
            {
                check();
            }
        }, checkInterval, checkInterval, TimeUnit.MILLISECONDS );
    }

    private void check()
    {
        synchronized ( LazyFileWriter.this )
        {
            if ( session == null || session.lastWrite == 0 )
            {
                return;
            }
            long now = System.currentTimeMillis();
            try
            {
                // For new writes, flush if last flush is earlier than threshold
                if ( session.lastFlush <= session.lastWrite && session.lastFlush + leastFlushInterval < now )
                {
                    session.writer.flush();
                    session.lastFlush = now;

                    File expectedFile = outputFileProvider.provide();
                    if ( !session.outputFile.equals( expectedFile ) )
                    {
                        if ( LOG.isDebugEnabled() )
                        {
                            LOG.info( "About to rotate from " + session.outputFile + " to new file " + expectedFile );
                        }
                        close();
                        return;
                    }
                }

                // Close session if it has been idle for longer than threshold
                if ( session.lastWrite + maxIdle < now )
                {
                    close();
                }
            }
            catch ( IOException e )
            {
                LOG.error( "Can't check file marker:" + e.getMessage(), e );
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void close()
        throws IOException
    {
        synchronized ( this )
        {
            if ( session != null )
            {
                if ( LOG.isDebugEnabled() )
                {
                    LOG.debug( "Closing session for file " + session.outputFile );
                }
                session.writer.flush();
                session.writer.close();
                session = null;
            }
        }
    }

    @Override
    public void finalize()
    {
        try
        {
            close();
        }
        catch ( IOException e )
        {
            LOG.warn( "Can't close", e );
        }
    }

    private Writer getWriter()
    {
        synchronized ( this )
        {
            if ( session != null )
            {
                return session.writer;
            }
        }
        throw new IllegalStateException( "Writer should be available now" );
    }

    public final void setLeastFlushInterval( long leastFlushInterval )
    {
        this.leastFlushInterval = leastFlushInterval;
    }

    public final void setMaxIdle( long maxIdle )
    {
        this.maxIdle = maxIdle;
    }

    void write( String bucket, Iterable<Mark> marks, long timestamp )
        throws IOException
    {
        synchronized ( this )
        {
            if ( session == null )
            {
                session = new Session( outputFileProvider.provide() );
                if ( !session.appending )
                {
                    output.writeHeader( application );
                }
            }
            output.writeBody( bucket, marks, timestamp, application );
            session.lastWrite = System.currentTimeMillis();
        }
    }
}
