package org.cyclopsgroup.streammarker.io;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.ConstantProvider;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Provider;

public class TextWriterMarkOutput
    implements MarkOutput
{
    private static String setOf( Collection<String> values )
    {
        if ( values == null || values.isEmpty() )
        {
            return "[]";
        }
        return "[\"" + StringUtils.join( values, "\",\"" ) + "\"]";
    }

    private final Provider<Writer> writer;

    public TextWriterMarkOutput( final Writer writer )
    {
        this( ConstantProvider.of( writer ) );
    }

    public TextWriterMarkOutput( Provider<Writer> writer )
    {
        Validate.notNull( writer, "Writer can't be NULL" );
        this.writer = writer;
    }

    public void writeBody( String bucket, Iterable<Mark> marks, long timestamp, Application application )
        throws IOException
    {
        StringBuilder s = new StringBuilder();
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
            s.append( String.format( "{N:\"%s\",K:\"%s\",V:%d,T:%s}", mark.getName(), mark.getKind().getShortName(),
                                     mark.getValue(), setOf( mark.getTags() ) ) );
        }
        writeLine( String.format( "B:{A:\"%s\",B:\"%s\",T:%d,M:[%s]}", application.getApplicationIdentifier(), bucket,
                                  timestamp, s.toString() ) );
    }

    public void writeHeader( String fileId, Application application )
        throws IOException
    {
        writeLine( String.format( "H:{L:\"%s\",A:\"%s\",I:\"%s\",S:%d}", application.getLocationName(),
                                  application.getApplicationName(), fileId, System.currentTimeMillis() ) );
    }

    private void writeLine( String line )
        throws IOException
    {
        Writer w = writer.provide();
        synchronized ( w )
        {
            w.write( line );
            w.write( SystemUtils.LINE_SEPARATOR );
        }
    }
}
