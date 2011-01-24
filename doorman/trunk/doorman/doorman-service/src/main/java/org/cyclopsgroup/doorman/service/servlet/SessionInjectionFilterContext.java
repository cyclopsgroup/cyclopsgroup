package org.cyclopsgroup.doorman.service.servlet;

import org.apache.commons.lang.Validate;
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

    private static final int DEFAULT_SESSION_CHECKING_INTERVAL = 300;

    private static final String DEFAULT_SESSION_ID_COOKIE = "doormanSessionId";

    private boolean redirectingToUrl;

    private String sessionAttribute = DEFAULT_SESSION_ATTRIBUTE;

    private int sessionCheckingInterval = DEFAULT_SESSION_CHECKING_INTERVAL;

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
     * Interval in seconds between which session is checked against server
     *
     * @return Seconds of interval
     */
    public final int getSessionCheckingInterval()
    {
        return sessionCheckingInterval;
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
     * @return True if sign in URL is redirected instead of forwarded
     */
    public final boolean isRedirectingToUrl()
    {
        return redirectingToUrl;
    }

    /**
     * @param redirectingToUrl True if sign in URL is redirected instead of forwarded
     */
    public final void setRedirectingToUrl( boolean redirectingToUrl )
    {
        this.redirectingToUrl = redirectingToUrl;
    }

    /**
     * @param sessionAttribute {@link #getSessionAttribute()}
     */
    public final void setSessionAttribute( String sessionAttribute )
    {
        this.sessionAttribute = sessionAttribute;
    }

    /**
     * @param sessionCheckingInterval {@link #getSessionCheckingInterval()}
     */
    public final void setSessionCheckingInterval( int sessionCheckingInterval )
    {
        Validate.isTrue( sessionCheckingInterval > 0, "Invalid interval value " + sessionCheckingInterval );
        this.sessionCheckingInterval = sessionCheckingInterval;
    }

    /**
     * @param sessionIdCookie {@link #getSessionIdCookie()}
     */
    public final void setSessionIdCookie( String sessionIdCookie )
    {
        this.sessionIdCookie = sessionIdCookie;
    }
}
