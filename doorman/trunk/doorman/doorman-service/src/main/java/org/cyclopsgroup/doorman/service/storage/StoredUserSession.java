package org.cyclopsgroup.doorman.service.storage;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    private Timestamp lastModified;

    private Date lastVerification;

    private String sessionId;

    private StoredUser user;

    private String userAgent;

    @Column( name = "accept_language", length = 8 )
    public final String getAcceptLanguage()
    {
        return acceptLanguage;
    }

    @Column( name = "creation_date", nullable = false )
    @Temporal( TemporalType.DATE )
    public final Date getCreationDate()
    {
        return creationDate;
    }

    @Column( name = "id_address", length = 16 )
    public final String getIpAddress()
    {
        return ipAddress;
    }

    @Column( name = "last_activity", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    public final Timestamp getLastModified()
    {
        return lastModified;
    }

    @Column( name = "last_verification", nullable = false )
    @Temporal( TemporalType.DATE )
    public final Date getLastVerification()
    {
        return lastVerification;
    }

    @Id
    @Column( name = "session_id", nullable = false, length = 32 )
    public final String getSessionId()
    {
        return sessionId;

    }

    @ManyToOne( optional = false, fetch = FetchType.EAGER )
    @JoinColumn( name = "user_id", nullable = false, updatable = false )
    public final StoredUser getUser()
    {
        return user;
    }

    @Column( name = "user_agent", length = 32 )
    public final String getUserAgent()
    {
        return userAgent;
    }

    public final void setAcceptLanguage( String acceptLanguage )
    {
        this.acceptLanguage = acceptLanguage;
    }

    public final void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    public final void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }

    public final void setLastActivity( Timestamp lastModified )
    {
        this.lastModified = lastModified;
    }

    public final void setLastVerification( Date lastVerification )
    {
        this.lastVerification = lastVerification;
    }

    public final void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    public final void setUser( StoredUser user )
    {
        this.user = user;
    }

    public final void setUserAgent( String userAgent )
    {
        this.userAgent = userAgent;
    }
}
