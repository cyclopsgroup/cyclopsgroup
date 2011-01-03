package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

/**
 * A session attached to one physical client
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "UserSession" )
public class UserSession
{
    private UserSessionAttributes attributes;

    private DateTime creationDate;

    private DateTime lastActivity;

    private DateTime lastVerification;

    private String sessionId;

    private User user;

    /**
     * Pass all values to another object
     *
     * @param other Another user session object
     */
    public void copyTo( UserSession other )
    {
        other.setAttributes( attributes );
        other.setCreationDate( creationDate );
        other.setLastActivity( lastActivity );
        other.setLastVerification( lastVerification );
        other.setSessionId( sessionId );
        other.setUser( user );
    }

    /**
     * @return Attributes attached to session
     */
    @XmlElement
    public final UserSessionAttributes getAttributes()
    {
        return attributes;
    }

    /**
     * @return Immutable creation date of session
     */
    @XmlElement
    public final DateTime getCreationDate()
    {
        return creationDate;
    }

    /**
     * @return Timestamp of last write operation of this session
     */
    @XmlElement
    public final DateTime getLastActivity()
    {
        return lastActivity;
    }

    /**
     * @return Last time the session is authenticated with good credential
     */
    @XmlElement
    public final DateTime getLastVerification()
    {
        return lastVerification;
    }

    /**
     * @return User POJO, which is never NULL. If user isn't authenticated yet, a {@link UnauthenticatedError} is thrown
     * @throws UnauthenticatedError When user is not authenticated
     */
    public User getRequiredUser()
        throws UnauthenticatedError
    {
        User u = user;
        if ( u == null )
        {
            throw new UnauthenticatedError();
        }
        return u;
    }

    /**
     * @return Id of this session
     */
    @XmlElement
    public final String getSessionId()
    {
        return sessionId;
    }

    /**
     * @return Attached user of this session
     */
    @XmlElement
    public final User getUser()
    {
        return user;
    }

    /**
     * @param attributes {@link #getAttributes()}
     */
    public final void setAttributes( UserSessionAttributes attributes )
    {
        this.attributes = attributes;
    }

    /**
     * @param creationDate {@link #getCreationDate()}
     */
    public final void setCreationDate( DateTime creationDate )
    {
        this.creationDate = creationDate;
    }

    /**
     * @param lastActivity {@link #getLastActivity()}
     */
    public final void setLastActivity( DateTime lastActivity )
    {
        this.lastActivity = lastActivity;
    }

    /**
     * @param lastVerification {@link #getLastVerification()}
     */
    public final void setLastVerification( DateTime lastVerification )
    {
        this.lastVerification = lastVerification;
    }

    /**
     * @param sessionId {@link #getSessionId()}
     */
    public final void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    /**
     * @param user {@link #getUser()}
     */
    public final void setUser( User user )
    {
        this.user = user;
    }
}
