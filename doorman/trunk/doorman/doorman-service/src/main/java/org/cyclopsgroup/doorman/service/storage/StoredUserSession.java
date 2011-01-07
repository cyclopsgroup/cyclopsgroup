package org.cyclopsgroup.doorman.service.storage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Java POJO of user session
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Entity
@Table( name = "dm_session" )
@org.hibernate.annotations.Entity( dynamicUpdate = true )
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

    /**
     * @return Accept language from browser
     */
    @Column( name = "accept_language", length = 16 )
    public String getAcceptLanguage()
    {
        return acceptLanguage;
    }

    /**
     * @return Creation date of session
     */
    @Column( name = "creation_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * @return IP address of session
     */
    @Column( name = "id_address", length = 16 )
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * @return Last modified time of session
     */
    @Column( name = "last_modified", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getLastModified()
    {
        return lastModified;
    }

    /**
     * @return Time stamp of last authenticatino
     */
    @Column( name = "last_verification" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getLastVerification()
    {
        return lastVerification;
    }

    /**
     * @return Primary key of session
     */
    @Id
    @Column( name = "session_id", nullable = false, length = 32 )
    public String getSessionId()
    {
        return sessionId;

    }

    /**
     * @return {@link #getUser()}
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    public StoredUser getUser()
    {
        return user;
    }

    /**
     * @return {@link #getUserAgent()}
     */
    @Column( name = "user_agent", length = 256 )
    public String getUserAgent()
    {
        return userAgent;
    }

    /**
     * @param acceptLanguage {@link #getAcceptLanguage()}
     */
    public void setAcceptLanguage( String acceptLanguage )
    {
        this.acceptLanguage = acceptLanguage;
    }

    /**
     * @param creationDate {@link #getCreationDate()}
     */
    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    /**
     * @param ipAddress {@link #getIpAddress()}
     */
    public void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }

    /**
     * @param lastModified {@link #getLastModified()}
     */
    public void setLastModified( Date lastModified )
    {
        this.lastModified = lastModified;
    }

    /**
     * @param lastVerification {@link #getLastVerification()}
     */
    public void setLastVerification( Date lastVerification )
    {
        this.lastVerification = lastVerification;
    }

    /**
     * @param sessionId {@link #getSessionId()}
     */
    public void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    /**
     * @param user {@link #getUser()}
     */
    public void setUser( StoredUser user )
    {
        this.user = user;
    }

    /**
     * @param userAgent {@link #getUserAgent()}
     */
    public void setUserAgent( String userAgent )
    {
        this.userAgent = userAgent;
    }
}
