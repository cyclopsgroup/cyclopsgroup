package org.cyclopsgroup.doorman.service.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * A filter that figures out user session based on local cookie, create session if it doesn't exist, and store session
 * in cookie
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SessionInjectionFilter
    implements Filter
{
    private static final Log LOG = LogFactory.getLog( SessionInjectionFilter.class );

    private static final String USER_SESSION_NAME = UserSession.class.getName();

    private SessionService service;

    /**
     * @inheritDoc
     */
    @Override
    public void destroy()
    {
    }

    /**
     * @inheritDoc
     */
    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;

        UserSession session = (UserSession) req.getSession().getAttribute( USER_SESSION_NAME );
        if ( session == null )
        {
            LOG.info( "Looking for sessionId cookie from request cookies: " + Arrays.toString( req.getCookies() ) );
            Cookie sessionIdCookie = null;
            if ( req.getCookies() != null )
            {
                for ( Cookie c : req.getCookies() )
                {
                    if ( c.getName().equals( "sessionId" ) )
                    {
                        sessionIdCookie = c;
                    }
                }
            }
            LOG.info( "Found cookie " + ToStringBuilder.reflectionToString( sessionIdCookie ) );
            if ( sessionIdCookie != null )
            {
                session = service.getSession( sessionIdCookie.getValue() );
                LOG.info( "Found existing session from session service: "
                    + ToStringBuilder.reflectionToString( session ) );
            }

            if ( session == null || sessionIdCookie == null )
            {
                String sessionId = UUID.randomUUID().toString();
                UserSessionAttributes attributes = new UserSessionAttributes();
                attributes.setAcceptLanguage( req.getHeader( "Accept-Language" ) );
                attributes.setUserAgent( req.getHeader( "User-Agent" ) );
                attributes.setIpAddress( req.getRemoteAddr() );

                LOG.info( "Start new session for " + sessionId + " with attributes "
                    + ToStringBuilder.reflectionToString( attributes ) );
                session = service.startSession( sessionId, attributes );
                sessionIdCookie = new Cookie( "sessionId", sessionId );
            }
            req.getSession().setAttribute( USER_SESSION_NAME, session );
            sessionIdCookie.setMaxAge( 24 * 3600 );
            ( (HttpServletResponse) response ).addCookie( sessionIdCookie );
        }
        chain.doFilter( request, response );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void init( FilterConfig config )
        throws ServletException
    {
        String name = config.getInitParameter( "sessionServiceName" );
        if ( StringUtils.isBlank( name ) )
        {
            name = SessionService.class.getName();
        }
        LOG.info( "Name of SessionService in context is " + name );
        WebApplicationContext applicationContext =
            WebApplicationContextUtils.getRequiredWebApplicationContext( config.getServletContext() );
        service = (SessionService) applicationContext.getBean( name, SessionService.class );
    }
}
