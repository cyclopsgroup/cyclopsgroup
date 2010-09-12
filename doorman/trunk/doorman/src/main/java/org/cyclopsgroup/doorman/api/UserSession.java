package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "UserSession" )
public class UserSession
{
    private UserSessionAttributes attributes;

    private String sessionId;

    private User user;

    // private DateTime creationDate;
    // private DateTime lastVerification;
    // private DateTime lastActivity;

    @XmlElement
    public final UserSessionAttributes getAttributes()
    {
        return attributes;
    }

    @XmlElement
    public final String getSessionId()
    {
        return sessionId;
    }

    @XmlElement
    public final User getUser()
    {
        return user;
    }

    public final void setAttributes( UserSessionAttributes attributes )
    {
        this.attributes = attributes;
    }

    public final void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    public final void setUser( User user )
    {
        this.user = user;
    }
}
