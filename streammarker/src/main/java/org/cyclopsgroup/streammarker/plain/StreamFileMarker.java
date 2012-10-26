package org.cyclopsgroup.streammarker.plain;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Marker;

public class StreamFileMarker
    implements Marker, Closeable
{
    private class DrawJob
        implements Runnable
    {
        private final String bucket;

        private final Iterable<Mark> marks;

        private final long timestamp = System.currentTimeMillis();

        private DrawJob( String bucket, Iterable<Mark> marks )
        {
            this.bucket = bucket;
            this.marks = marks;
        }

        public void run()
        {
            try
            {
                output.write( bucket, timestamp, marks );
            }
            catch ( IOException e )
            {
                LOG.error( "Can't draw marks " + marks + " to bucket " + bucket, e );
            }
        }
    }

    private static final Log LOG = LogFactory.getLog( StreamFileMarker.class );

    private final ScheduledExecutorService background =
        Executors.newSingleThreadScheduledExecutor( new DaemonThreadFactory( "marker-" + hashCode() ) );

    private final StreamMarksWriter output;

    public StreamFileMarker( StreamMarksWriter output )
        throws IOException
    {
        this.output = output;
    }

    /**
     * @throws IOException
     */
    @Override
    public void close()
        throws IOException
    {
        output.close();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( String bucket, Iterable<Mark> marks )
    {
        background.execute( new DrawJob( bucket, marks ) );
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
