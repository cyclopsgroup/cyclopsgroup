package org.cyclopsgroup.doorman.service.storage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "dm_user_session" )
public class StoredUserSession
{
    private String acceptLanguage;

    private Date creationDate;

    private String ipAddress;

    private Date lastModified;

    private Date lastVerification;

    private String sessionId;

    private StoredUser user;

    private String userAgent;

    @Column( name = "accept_language", length = 8 )
    public String getAcceptLanguage()
    {
        return acceptLanguage;
    }

    @Column( name = "creation_date", nullable = false )
    @Temporal( TemporalType.DATE )
    public Date getCreationDate()
    {
        return creationDate;
    }

    @Column( name = "id_address", length = 16 )
    public String getIpAddress()
    {
        return ipAddress;
    }

    @Column( name = "last_activity", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getLastModified()
    {
        return lastModified;
    }

    @Column( name = "last_verification" )
    @Temporal( TemporalType.DATE )
    public Date getLastVerification()
    {
        return lastVerification;
    }

    @Id
    @Column( name = "session_id", nullable = false, length = 32 )
    public String getSessionId()
    {
        return sessionId;

    }

    @ManyToOne( optional = true )
    @JoinColumn( name = "user_id" )
    public StoredUser getUser()
    {
        return user;
    }

    @Column( name = "user_agent", length = 32 )
    public String getUserAgent()
    {
        return userAgent;
    }

    public void setAcceptLanguage( String acceptLanguage )
    {
        this.acceptLanguage = acceptLanguage;
    }

    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    public void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }

    public void setLastModified( Date lastModified )
    {
        this.lastModified = lastModified;
    }

    public void setLastVerification( Date lastVerification )
    {
        this.lastVerification = lastVerification;
    }

    public void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    public void setUser( StoredUser user )
    {
        this.user = user;
    }

    public void setUserAgent( String userAgent )
    {
        this.userAgent = userAgent;
    }
}
