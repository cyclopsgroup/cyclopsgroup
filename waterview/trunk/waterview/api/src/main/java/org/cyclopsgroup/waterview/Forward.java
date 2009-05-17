package org.cyclopsgroup.waterview;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Server side redirection
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class Forward
    extends PageRedirection
{
    /**
     * @inheritDoc
     */
    @Override
    public void redirect( HttpServletRequest request, HttpServletResponse response )
        throws IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher( path );
        try
        {
            dispatcher.forward( request, response );
        }
        catch ( ServletException e )
        {
            throw new IOException( "Can't forward to " + path + " using request(" + request + ") and response (" +
                response + "): " + e.getMessage(), e );
        }
    }

    private final String path;

    /**
     * @param path Path response forwards to
     */
    public Forward( String path )
    {
        if ( path == null )
        {
            throw new NullPointerException( "Path to forward can't be NULL" );
        }
        this.path = path;
    }
}
