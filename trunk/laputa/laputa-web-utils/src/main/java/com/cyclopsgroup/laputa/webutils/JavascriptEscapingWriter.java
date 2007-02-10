package com.cyclopsgroup.laputa.webutils;

import java.io.IOException;
import java.io.Writer;

public class JavascriptEscapingWriter
    extends Writer
{
    private static final String JSLINE_PREFIX = "document.writeln(\"";

    private static final String JSLINE_SUFFIX = "\");\n";

    private boolean inline = false;

    private Writer proxyWriter;

    public JavascriptEscapingWriter( Writer writer )
    {
        proxyWriter = writer;
    }

    @Override
    public void close()
        throws IOException
    {
        if ( inline )
        {
            proxyWriter.write( JSLINE_SUFFIX );
            inline = false;
        }

        flush();
        proxyWriter.close();
    }

    @Override
    public void flush()
        throws IOException
    {
        proxyWriter.flush();
    }

    @Override
    public void write( char[] cbuf, int off, int len )
        throws IOException
    {
        for ( int i = 0; i < len; i++ )
        {
            char c = cbuf[off + i];
            if ( !inline )
            {
                proxyWriter.write( JSLINE_PREFIX );
                inline = true;
            }
            if ( c == '\n' )
            {
                proxyWriter.write( JSLINE_SUFFIX );
                inline = false;
            }
            else if ( c == '\'' || c == '"' || c == '\'' )
            {
                proxyWriter.write( '\\' );
                proxyWriter.write( c );
            }
            else
            {
                proxyWriter.write( c );
            }
        }
    }
}