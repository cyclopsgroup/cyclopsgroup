package org.cyclopsgroup.waterview;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Client side redirection
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public final class Redirection
    extends PageRedirection
{
    private final String queryString;

    private final String url;

    /**
     * @param url URL it redirects to
     */
    public Redirection( String url )
    {
        this( url, null );
    }

    /**
     * @param url URL it redirects to
     * @param queryString Query string appended to URL
     */
    public Redirection( String url, String queryString )
    {
        if ( url == null )
        {
            throw new NullPointerException( "URL can't be NULL" );
        }
        this.url = url;
        this.queryString = queryString;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void redirect( HttpServletRequest request, HttpServletResponse response )
        throws IOException
    {
        String dest = url + queryString == null ? "" : "?" + queryString;
        response.sendRedirect( dest );
    }
}
