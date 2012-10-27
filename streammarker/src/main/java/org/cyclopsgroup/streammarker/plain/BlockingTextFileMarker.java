package org.cyclopsgroup.streammarker.plain;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Marker;
import org.cyclopsgroup.streammarker.io.MarkOutput;
import org.cyclopsgroup.streammarker.io.TextWriterMarkOutput;

class BlockingTextFileMarker
    implements Marker, Closeable
{
    private static final Log LOG = LogFactory.getLog( BlockingTextFileMarker.class );

    private final MarkOutput output;

    private final Application application;

    private final Writer fileWriter;

    BlockingTextFileMarker( File outputFile, Application application )
        throws IOException
    {
        boolean newFile = !outputFile.isFile();
        FileWriter w;
        if ( newFile )
        {
            LOG.info( "Creating new file " + outputFile );
            w = new FileWriter( outputFile );
        }
        else
        {
            LOG.info( "Appending existing file " + outputFile );
            w = new FileWriter( outputFile, true );
        }
        fileWriter = new BufferedWriter( w );
        this.output = new TextWriterMarkOutput( fileWriter );
        this.application = application;
        if ( newFile )
        {
            output.writeHeader( application );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw( String bucket, Iterable<Mark> marks )
    {
        try
        {
            output.writeBody( bucket, marks, System.currentTimeMillis(), 0, application );
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
    public void draw( String bucket, Mark... marks )
    {
        draw( bucket, new ArrayIterable<Mark>( marks ) );
    }

    @Override
    public void close()
        throws IOException
    {
        synchronized ( fileWriter )
        {
            output.writeFooter( 0, application );
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
