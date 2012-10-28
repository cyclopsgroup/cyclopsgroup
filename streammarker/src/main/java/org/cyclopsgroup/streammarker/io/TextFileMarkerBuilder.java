package org.cyclopsgroup.streammarker.io;

import java.io.File;
import java.io.IOException;

import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.Marker;
import org.cyclopsgroup.streammarker.MarkerBuilder;
import org.cyclopsgroup.streammarker.Provider;

public class TextFileMarkerBuilder
    implements MarkerBuilder
{
    private static void notNull( Object object, String name )
    {
        if ( object == null )
        {
            throw new IllegalStateException( "Required property [" + name + "] has not been set yet" );
        }
    }

    private Application application;

    private boolean blocking;

    private long checkIntervalMillis = LazyFileWriter.DEFAULT_CHECK_INTERVAL;

    private long leastFlushIntervalMillis = LazyFileWriter.DEFAULT_LEAST_FLUSH_INTERVAL;

    private long maxIdleMillis = LazyFileWriter.DEFAULT_MAX_IDLE;

    private Provider<File> outputFile;

    /**
     * @inheritDoc
     */
    @Override
    public Marker build()
        throws IOException
    {
        notNull( application, "application" );
        notNull( outputFile, "outputFile" );

        LazyFileWriter writer = new LazyFileWriter( application, outputFile, checkIntervalMillis );
        writer.setLeastFlushInterval( leastFlushIntervalMillis );
        writer.setMaxIdle( maxIdleMillis );
        return new TextFileMarker( writer, blocking );
    }

    /**
     * @see #setApplication(Application)
     */
    public TextFileMarkerBuilder of( Application application )
    {
        setApplication( application );
        return this;
    }

    public final void setApplication( Application application )
    {
        this.application = application;
    }

    public final void setBlocking( boolean blocking )
    {
        this.blocking = blocking;
    }

    public final void setCheckIntervalMillis( long checkIntervalMillis )
    {
        this.checkIntervalMillis = checkIntervalMillis;
    }

    public final void setLeastFlushIntervalMillis( long leastFlushIntervalMillis )
    {
        this.leastFlushIntervalMillis = leastFlushIntervalMillis;
    }

    public final void setMaxIdleMillis( long maxIdleMillis )
    {
        this.maxIdleMillis = maxIdleMillis;
    }

    public final void setOutputFile( Provider<File> outputFile )
    {
        this.outputFile = outputFile;
    }

    /**
     * @see #setOutputFile(Provider)
     */
    public TextFileMarkerBuilder to( File fileBase, String suffixFormat )
    {
        return to( new DatePatternFileProvider( fileBase, suffixFormat ) );
    }

    /**
     * @see #setOutputFile(Provider)
     */
    public TextFileMarkerBuilder to( Provider<File> outputFile )
    {
        setOutputFile( outputFile );
        return this;
    }

    /**
     * @see #setBlocking(boolean)
     */
    public TextFileMarkerBuilder withBlocking( boolean blocking )
    {
        setBlocking( blocking );
        return this;
    }

    /**
     * @see #setCheckIntervalMillis(long)
     */
    public TextFileMarkerBuilder withCheckIntervalMillis( long interval )
    {
        setCheckIntervalMillis( interval );
        return this;
    }

    /**
     * @see #setLeastFlushIntervalMillis(long)
     */
    public TextFileMarkerBuilder withLeastFlushIntervalMillis( long interval )
    {
        setLeastFlushIntervalMillis( interval );
        return this;
    }

    /**
     * @see #setMaxIdleMillis(long)
     */
    public TextFileMarkerBuilder withMaxIdleMillis( long maxIdleMillis )
    {
        setMaxIdleMillis( maxIdleMillis );
        return this;
    }
}
