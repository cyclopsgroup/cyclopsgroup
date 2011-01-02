package org.cyclopsgroup.doorman.service.servlet;

import org.cyclopsgroup.doorman.api.UserSession;

/**
 * Configuration for session injection filter
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @see {@link SessionInjectionFilter}
 */
public class SessionInjectionFilterConfig
{
    private static final String DEFAULT_SESSION_ATTRIBUTE = UserSession.class.getName();

    private static final String DEFAULT_SESSION_ID_COOKIE = "doormanSessionId";

    private String sessionAttribute = DEFAULT_SESSION_ATTRIBUTE;

    private String sessionIdCookie = DEFAULT_SESSION_ID_COOKIE;

    private String signInUrl;

    /**
     * @return Name of session attribute that stores user session
     */
    public final String getSessionAttribute()
    {
        return sessionAttribute;
    }

    /**
     * @return Name of cookie that stores session ID
     */
    public final String getSessionIdCookie()
    {
        return sessionIdCookie;
    }

    /**
     * @return When destination requires user identity, page is redirected to this URL to let user sign in
     */
    public final String getSignInUrl()
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

    /**
     * @param signInUrl {@link #getSignInUrl()}
     */
    public final void setSignInUrl( String signInUrl )
    {
        this.signInUrl = signInUrl;
    }

}
