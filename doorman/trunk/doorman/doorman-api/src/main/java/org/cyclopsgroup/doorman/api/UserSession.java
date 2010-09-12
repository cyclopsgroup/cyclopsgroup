package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement( name = "UserSession" )
public class UserSession
{
    private UserSessionAttributes attributes;

    private DateTime creationDate;

    private DateTime lastActivity;

    private DateTime lastVerification;

    private String sessionId;

    private User user;

    @XmlElement
    public final UserSessionAttributes getAttributes()
    {
        return attributes;
    }

    @XmlElement
    public final DateTime getCreationDate()
    {
        return creationDate;
    }

    @XmlElement
    public final DateTime getLastActivity()
    {
        return lastActivity;
    }

    @XmlElement
    public final DateTime getLastVerification()
    {
        return lastVerification;
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

    public final void setCreationDate( DateTime creationDate )
    {
        this.creationDate = creationDate;
    }

    public final void setLastActivity( DateTime lastActivity )
    {
        this.lastActivity = lastActivity;
    }

    public final void setLastVerification( DateTime lastVerification )
    {
        this.lastVerification = lastVerification;
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
