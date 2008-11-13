package com.cyclopsgroup.waterview.spi;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class MockRunData
    extends AbstractRunData
{
    public MockRunData( Writer output )
    {
        if ( output instanceof PrintWriter )
        {
            setOutput( (PrintWriter) output );
        }
        else
        {
            setOutput( new PrintWriter( output ) );
        }
    }

    public String getMimeType( String fileName )
    {
        return "text/html";
    }

    @Override
    public OutputStream getOutputStream()
    {
        throw new UnsupportedOperationException( "" );
    }

    public void setOutputContentType( String contentType )
    {
    }
}