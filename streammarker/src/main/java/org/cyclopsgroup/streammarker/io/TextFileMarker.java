package org.cyclopsgroup.streammarker.io;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.ArrayIterable;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Marker;
import org.cyclopsgroup.streammarker.Provider;

public class TextFileMarker
    implements Marker, Closeable
{
    private final LazyFileWriter writer;

    private final ExecutorService background;

    private final boolean blocking;

    TextFileMarker( LazyFileWriter writer, boolean blocking )
    {
        this.writer = writer;
        this.blocking = blocking;
        if ( blocking )
        {
            background = null;
        }
        else
        {
            background = Executors.newSingleThreadExecutor();
        }
    }

    public TextFileMarker( Provider<File> outputFile, Application application, boolean blocking )
        throws IOException
    {
        this( new LazyFileWriter( application, outputFile ), blocking );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void close()
        throws IOException
    {
        try
        {
            if ( !blocking )
            {
                background.shutdownNow();
                try
                {
                    background.awaitTermination( 10, TimeUnit.SECONDS );
                }
                catch ( InterruptedException e )
                {
                    throw new IOException( "Shutdown thread is interrupted", e );
                }
            }
        }
        finally
        {
            writer.close();
        }
    }

    private void doDraw( String bucket, Iterable<Mark> marks, long timestamp )
    {
        try
        {
            writer.write( bucket, marks, timestamp );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "Can't draw mark to bucket " + bucket, e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( final String bucket, final Iterable<Mark> marks )
    {
        final long now = System.currentTimeMillis();
        if ( blocking )
        {
            doDraw( bucket, marks, now );
        }
        else
        {
            background.submit( new Runnable()
            {
                public void run()
                {
                    doDraw( bucket, marks, now );
                }
            } );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( String bucket, Mark... marks )
    {
        draw( bucket, new ArrayIterable<Mark>( marks ) );
    }
}
