package org.cyclopsgroup.doorman.service.servlet;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.UserSession;

/**
 * Configuration for session injection filter
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @see {@link SessionInjectionFilter}
 */
public class SessionInjectionFilterContext
{
    private static final String DEFAULT_SESSION_ATTRIBUTE = UserSession.class.getName();

    private static final String DEFAULT_SESSION_ID_COOKIE = "doormanSessionId";

    private String sessionAttribute = DEFAULT_SESSION_ATTRIBUTE;

    private String sessionIdCookie = DEFAULT_SESSION_ID_COOKIE;

    private final SessionService sessionService;

    private final String signInUrl;

    /**
     * @param sessionService Session service interface
     * @param signInUrl URL to redirect to to sign in
     */
    public SessionInjectionFilterContext( SessionService sessionService, String signInUrl )
    {
        this.sessionService = sessionService;
        this.signInUrl = signInUrl;
    }

    /**
     * @return Name of session attribute that stores user session
     */
    final String getSessionAttribute()
    {
        return sessionAttribute;
    }

    /**
     * @return Name of cookie that stores session ID
     */
    final String getSessionIdCookie()
    {
        return sessionIdCookie;
    }

    final SessionService getSessionService()
    {
        return sessionService;
    }

    /**
     * @return When destination requires user identity, page is redirected to this URL to let user sign in
     */
    final String getSignInUrl()
    {
        return signInUrl;
    }

    /**
     * @param sessionAttribute {@link #getSessionAttribute()}
     */
    public final void setSessionAttribute( String sessionAttribute )
    {
        this.sessionAttribute = sessionAttribute;
    }

    /**
     * @param sessionIdCookie {@link #getSessionIdCookie()}
     */
    public final void setSessionIdCookie( String sessionIdCookie )
    {
        this.sessionIdCookie = sessionIdCookie;
    }
}
