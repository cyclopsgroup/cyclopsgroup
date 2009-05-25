package org.cyclopsgroup.gallerian.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
    private static final String BROWSE_ACTION = "/browse.do";

    private static final String DEFAULT_CONTNET_PATH = "/c/";

    private static final String DEFAULT_TYPE_PATH = "/type/";

    private static final String DOWNLOAD_ACTION = "/download.do";

    private static final String ICON_ACTION = "/icon.do";

    private String startContentPath = DEFAULT_CONTNET_PATH;

    private String startTypePath = DEFAULT_TYPE_PATH;

    private String determineContentAction( String pathInfo, HttpServletRequest request, WebContext context )
    {
        String contentPath = pathInfo.substring( startContentPath.length() );
        String action;
        if ( pathInfo.endsWith( "/" ) )
        {
            action = BROWSE_ACTION;
        }
        else if ( StringUtils.isNotEmpty( request.getQueryString() ) )
        {
            action = "/" + request.getQueryString();
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
        context.setVariable( WebConstants.CONTENT_PATH, contentPath );
        return action;
    }

    private String determineTypeAction( String pathInfo, HttpServletRequest request, WebContext context )
    {
        String contentType = pathInfo.substring( startTypePath.length() );
        context.setVariable( WebConstants.CONTENT_TYPE, contentType );
        String action;
        if ( StringUtils.isEmpty( request.getQueryString() ) )
        {
            action = ICON_ACTION;
        }
        else
        {
            action = request.getQueryString();
        }
        return "/" + action;
    }

    /**
     * @return Value of field startPath
     */
    public final String getStartContentPath()
    {
        return startContentPath;
    }

    /**
     * @return Value of field startTypePath
     */
    public final String getStartTypePath()
    {
        return startTypePath;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();

        HttpServletRequest request = wc.getServletRequest();
        String pathInfo = request.getServletPath() + StringUtils.trimToEmpty( wc.getServletRequest().getPathInfo() );

        String action;

        if ( StringUtils.isEmpty( pathInfo ) )
        {
            throw new IllegalStateException( "Path info is NULL" );
        }
        else if ( pathInfo.startsWith( startContentPath ) )
        {
            action = determineContentAction( pathInfo, request, wc );
        }
        else if ( pathInfo.startsWith( startTypePath ) )
        {
            action = determineTypeAction( pathInfo, request, wc );
        }
        else
        {
            action = pathInfo;
        }
        context.setActions( new ArrayList<String>( Arrays.asList( action ) ) );
        context.invokeNext();
    }

    /**
     * @param startPath Value of field startPath to set
     */
    public final void setStartContentPath( String startPath )
    {
        this.startContentPath = startPath;
    }

    /**
     * @param startTypePath Value of field startTypePath to set
     */
    public final void setStartTypePath( String startTypePath )
    {
        this.startTypePath = startTypePath;
    }
}
