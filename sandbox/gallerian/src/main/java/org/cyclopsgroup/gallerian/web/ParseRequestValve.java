package org.cyclopsgroup.gallerian.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Valve to parse request URL in gallerian specific way
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ParseRequestValve
    implements Valve
{
    /**
     * @return Value of field startPath
     */
    public final String getStartPath()
    {
        return startPath;
    }

    /**
     * @param startPath Value of field startPath to set
     */
    public final void setStartPath( String startPath )
    {
        this.startPath = startPath;
    }

    private static final String BROWSE_ACTION = "/browse.vm";

    private static final String DOWNLOAD_ACTION = "/download.vm";

    private static final String DEFAULT_START_PATH = "/c/";

    private String startPath = DEFAULT_START_PATH;

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();

        String pathInfo =
            wc.getServletRequest().getServletPath() + StringUtils.trimToEmpty( wc.getServletRequest().getPathInfo() );

        String action;

        if ( StringUtils.isEmpty( pathInfo ) )
        {
            throw new IllegalStateException( "Path info is NULL" );
        }
        else if ( pathInfo.startsWith( startPath ) )
        {
            String contentPath = pathInfo.substring( startPath.length() );

            if ( pathInfo.endsWith( "/" ) )
            {
                action = BROWSE_ACTION;
            }
            else
            {
                action = DOWNLOAD_ACTION;
            }
            if ( StringUtils.isEmpty( contentPath ) )
            {
                contentPath = "/";
            }
            else if ( contentPath.charAt( contentPath.length() - 1 ) == '/' )
            {
                contentPath = contentPath.substring( 0, contentPath.length() - 1 );
            }
            if ( contentPath.charAt( 0 ) != '/' )
            {
                contentPath = "/" + contentPath;
            }
            wc.setVariable( "contentPath", contentPath );
        }
        else
        {
            action = pathInfo;
        }
        context.setActions( new ArrayList<String>( Arrays.asList( action ) ) );
        context.invokeNext();
    }
}
