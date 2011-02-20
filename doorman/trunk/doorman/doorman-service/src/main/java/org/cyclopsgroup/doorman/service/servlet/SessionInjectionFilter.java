package org.cyclopsgroup.doorman.service.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
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
import org.cyclopsgroup.caff.util.UUIDUtils;
import org.cyclopsgroup.doorman.api.UnauthenticatedError;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.joda.time.DateTime;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.NestedServletException;

/**
 * A filter that figures out user session based on local cookie, create session if it doesn't exist, and store session
 * in cookie
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SessionInjectionFilter
    implements Filter
{
    private static final String DEFAULT_CONTEXT_BEAN = "sessionInjectionFilterContext";

    private static final Log LOG = LogFactory.getLog( SessionInjectionFilter.class );

    private static String getParameter( FilterConfig config, String paramName, String defaultValue )
    {
        String value = config.getInitParameter( paramName );
        if ( StringUtils.isBlank( value ) )
        {
            value = defaultValue;
        }
        return value;
    }

    private SessionInjectionFilterContext context;

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
        DateTime now = new DateTime();
        HttpServletRequest req = (HttpServletRequest) request;
        UserSession session = (UserSession) req.getSession().getAttribute( context.getSessionAttribute() );
        if ( session == null
            || session.getLastActivity().plusSeconds( context.getSessionCheckingInterval() ).isBefore( now ) )
        {
            LOG.info( "Looking for sessionId cookie from request cookies: " + Arrays.toString( req.getCookies() ) );
            Cookie sessionIdCookie = null;
            if ( req.getCookies() != null )
            {
                for ( Cookie c : req.getCookies() )
                {
                    if ( c.getName().equals( context.getSessionIdCookie() ) )
                    {
                        sessionIdCookie = c;
                    }
                }
            }
            LOG.info( "Found cookie " + ToStringBuilder.reflectionToString( sessionIdCookie ) );
            if ( sessionIdCookie != null )
            {
                session = context.getSessionService().getSession( sessionIdCookie.getValue() );
                if ( session != null )
                {
                    LOG.info( "Found existing session from session service: "
                        + ToStringBuilder.reflectionToString( session ) + ", user="
                        + ToStringBuilder.reflectionToString( session.getUser() ) + ", attributes="
                        + ToStringBuilder.reflectionToString( session.getAttributes() ) );
                }
            }

            if ( session == null || sessionIdCookie == null )
            {
                String sessionId = UUIDUtils.randomStringId();
                UserSessionAttributes attributes = new UserSessionAttributes();
                attributes.setAcceptLanguage( req.getHeader( "Accept-Language" ) );
                attributes.setUserAgent( req.getHeader( "User-Agent" ) );
                attributes.setIpAddress( req.getRemoteAddr() );

                LOG.info( "Start new session for " + sessionId + " with attributes "
                    + ToStringBuilder.reflectionToString( attributes ) );
                session = context.getSessionService().startSession( sessionId, attributes );
                sessionIdCookie = new Cookie( context.getSessionIdCookie(), sessionId );
            }

            session.setLastActivity( now ); // FIXME This value should come from database
            req.getSession().setAttribute( context.getSessionAttribute(), session );
            sessionIdCookie.setMaxAge( 7 * 24 * 3600 );
            ( (HttpServletResponse) response ).addCookie( sessionIdCookie );
        }
        try
        {
            chain.doFilter( request, response );
        }
        catch ( UnauthenticatedError e )
        {
            forwardToSignInUrl( req, (HttpServletResponse) response );
        }
        catch ( NestedServletException e )
        {
            if ( e.getCause() instanceof UnauthenticatedError )
            {
                forwardToSignInUrl( req, (HttpServletResponse) response );
            }
            else
            {
                throw e;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void init( FilterConfig filterConfig )
        throws ServletException
    {
        String name = getParameter( filterConfig, "contextBean", DEFAULT_CONTEXT_BEAN );
        LOG.info( "Name of filter context bean in context is " + name );
        WebApplicationContext applicationContext =
            WebApplicationContextUtils.getRequiredWebApplicationContext( filterConfig.getServletContext() );
        context =
            (SessionInjectionFilterContext) applicationContext.getBean( name, SessionInjectionFilterContext.class );
    }

    private void forwardToSignInUrl( HttpServletRequest req, HttpServletResponse resp )
        throws IOException, ServletException
    {
        StringBuffer url = req.getRequestURL();
        if ( StringUtils.isNotBlank( req.getQueryString() ) )
        {
            url.append( "?" + req.getQueryString() );
        }
        String signInUrl = context.getSignInUrl();
        if ( signInUrl.indexOf( "{contextPath}" ) != -1 )
        {
            signInUrl = StringUtils.replace( signInUrl, "{contextPath}", req.getContextPath() );
        }
        if ( context.isRedirectingToUrl() )
        {
            resp.sendRedirect( signInUrl + "?redirectTo=" + resp.encodeRedirectURL( url.toString() ) );
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher( signInUrl );
        ParameterOverridingRequest request = new ParameterOverridingRequest( req );
        request.setParameter( "redirectTo", url.toString() );
        dispatcher.forward( request, resp );
    }
}
