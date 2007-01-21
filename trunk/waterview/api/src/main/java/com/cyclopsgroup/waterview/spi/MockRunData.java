package com.cyclopsgroup.waterview.spi;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;


public class MockRunData
    extends AbstractRunData
{
    private PrintWriter output;

    public MockRunData( Waterview waterview, Writer output )
    {
        super( waterview );
        if ( output instanceof PrintWriter )
        {
            this.output = (PrintWriter) output;
        }
        else
        {
            this.output = new PrintWriter( output );
        }
    }

    public String getMimeType( String fileName )
    {
        return "text/html";
    }

    public PrintWriter getOutput()
    {
        return output;
    }

    public OutputStream getOutputStream()
    {
        throw new UnsupportedOperationException( "" );
    }

    public void setOutputContentType( String contentType )
    {
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
}
