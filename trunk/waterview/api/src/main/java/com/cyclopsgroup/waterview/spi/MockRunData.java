package com.cyclopsgroup.waterview.spi;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class MockRunData
    extends AbstractRunData
{
    public MockRunData( Waterview waterview, Writer output )
    {
        super( waterview );
        if ( output instanceof PrintWriter )
        {
            setOutput( (PrintWriter) output );
        }
        else
        {
            setOutput( new PrintWriter( output ) );
        }
    }

    public void addRequest( final String packageAlias, final String requestPath )
    {
        getRequests().add( new Request()
        {

            public String getPackageAlias()
            {
                return packageAlias;
            }

            public String getRequestPath()
            {
                return requestPath;
            }
        } );
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