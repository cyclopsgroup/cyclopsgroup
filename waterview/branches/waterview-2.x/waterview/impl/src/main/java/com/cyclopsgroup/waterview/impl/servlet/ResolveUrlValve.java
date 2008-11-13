package com.cyclopsgroup.waterview.impl.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.impl.Constants;
import com.cyclopsgroup.waterview.impl.HttpError;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

public class ResolveUrlValve
    implements Valve, Constants
{

    public static final String SCHEME_PORT_MATCH = "|ftp:21|http:80|https:443|";;

    private boolean withServletPath = false;

    protected String getApplicationBaseUrl( HttpServletRequest request )
    {
        String scheme = request.getScheme();
        String url = scheme + "://" + request.getServerName();
        int port = request.getServerPort();
        if ( SCHEME_PORT_MATCH.indexOf( '|' + scheme + ':' + port + '|' ) == -1 )
        {
            url += ":" + port;
        }
        url += request.getContextPath();
        return url;
    }

    protected String getRequestPath( HttpServletRequest request )
    {
        String pathInfo = request.getPathInfo();
        if ( withServletPath )
        {
            String path = request.getServletPath();
            if ( pathInfo != null )
            {
                path += pathInfo;
            }
            return path;
        }
        return pathInfo == null ? StringUtils.EMPTY : pathInfo;
    }

    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) data.getRequestContext().get( HTTP_REQUEST );
        data.setApplicationBaseUrl( getApplicationBaseUrl( request ) );
        data.setRequestPath( getRequestPath( request ) );

        System.out.println( data.getRequestPath() + "@" + data.getApplicationBaseUrl() );

        try
        {
            context.invokeNextValve( data );
        }
        catch ( HttpError e )
        {
            HttpServletResponse response = (HttpServletResponse) data.getRequestContext().get( HTTP_RESPONSE );
            if ( StringUtils.isEmpty( e.getErrorMessage() ) )
            {
                response.sendError( e.getStatusCode() );
            }
            else
            {
                response.sendError( e.getStatusCode(), e.getErrorMessage() );
            }
        }
    }

    public boolean isWithServletPath()
    {
        return withServletPath;
    }

    public void setWithServletPath( boolean withServletPath )
    {
        this.withServletPath = withServletPath;
    }
}