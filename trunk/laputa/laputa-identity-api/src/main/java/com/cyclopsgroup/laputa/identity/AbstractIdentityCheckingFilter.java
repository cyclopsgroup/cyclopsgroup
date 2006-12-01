package com.cyclopsgroup.laputa.identity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractIdentityCheckingFilter
    implements Filter
{
    public static final String LOGIN_URL_PARAMETER_NAME = "loginUrl";

    private IdentityService identityService;

    private String loginUrl;

    protected abstract IdentityService createIdentityService( FilterConfig filterConfig )
        throws Exception;

    public void destroy()
    {
        // do nothing
    }

    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
        throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authenticatedUser = null;
        for ( Cookie cookie : req.getCookies() )
        {
            if ( cookie.getName().equals( IdentityService.USER_ATTRIBUTE_NAME ) )
            {
                //TODO decryption
                authenticatedUser = cookie.getValue();
            }
        }

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ( authenticatedUser == null )
        {
            resp.sendRedirect( loginUrl );
            return;
        }

        req.setAttribute( IdentityService.USER_ATTRIBUTE_NAME, authenticatedUser );
    }

    public final IdentityService getIdentityService()
    {
        return identityService;
    }

    public void init( FilterConfig filterConfig )
        throws ServletException
    {
        loginUrl = filterConfig.getInitParameter( LOGIN_URL_PARAMETER_NAME );

        try
        {
            identityService = createIdentityService( filterConfig );
        }
        catch ( ServletException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ServletException( e );
        }
    }

}
