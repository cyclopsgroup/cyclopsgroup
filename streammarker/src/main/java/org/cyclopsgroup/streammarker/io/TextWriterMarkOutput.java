package org.cyclopsgroup.streammarker.io;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.plain.Application;

public class TextWriterMarkOutput
    implements MarkOutput
{
    private static String setOf( String[] values )
    {
        if ( values == null || values.length == 0 )
        {
            return "[]";
        }
        return "[\"" + StringUtils.join( values, "\",\"" ) + "\"]";
    }

    private final WriterProvider writer;

    public TextWriterMarkOutput( final Writer writer )
    {
        this.writer = new WriterProvider()
        {
            public Writer getWriter()
            {
                return writer;
            }
        };
    }

    public TextWriterMarkOutput( WriterProvider writer )
    {
        Validate.notNull( writer, "Writer can't be NULL" );
        this.writer = writer;
    }

    public void writeBody( String bucket, Iterable<Mark> marks, long timestamp, int index, Application application )
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
        writeLine( String.format( "B:{I:%011d,A:\"%s\",B:\"%s\",T:%d,M:[%s]}", index,
                                  application.getApplicationIdentifier(), bucket, timestamp, s.toString() ) );
    }

    public void writeFooter( int lines, Application application )
        throws IOException
    {
        writeLine( String.format( "F:{C:%d,E:%d}", lines, System.currentTimeMillis() ) );
    }

    public void writeHeader( Application application )
        throws IOException
    {
        writeLine( String.format( "H:{L:\"%s\",A:\"%s\",S:%d}", application.getLocationName(),
                                  application.getApplicationName(), System.currentTimeMillis() ) );
    }

    private void writeLine( String line )
        throws IOException
    {
        Writer w = writer.getWriter();
        synchronized ( w )
        {
            w.write( line );
            w.write( SystemUtils.LINE_SEPARATOR );
        }
    }
}
