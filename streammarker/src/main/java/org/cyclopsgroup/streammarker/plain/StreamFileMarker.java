package org.cyclopsgroup.streammarker.plain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Marker;

public class StreamFileMarker
    implements Marker
{
    private static final Log LOG = LogFactory.getLog( StreamFileMarker.class );

    private class DrawJob
        implements Runnable
    {
        private final Iterable<Mark> marks;

        private final String bucket;

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

    private final Executor background = Executors.newSingleThreadExecutor( new DaemonThreadFactory( "marker-"
        + hashCode() ) );

    private final File directory;

    private final String fileName;

    private final StreamMarksWriter output;

    public StreamFileMarker( File directory, String fileName )
        throws IOException
    {
        if ( !directory.isDirectory() )
        {
            throw new FileNotFoundException( "Directory " + directory + " is not found" );
        }
        this.directory = directory;
        this.fileName = fileName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( String bucket, Mark... marks )
    {
        draw( bucket, new ArrayIterable<Mark>( marks ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( String bucket, Iterable<Mark> marks )
    {
        background.execute( new DrawJob( bucket, marks ) );
    }
}
