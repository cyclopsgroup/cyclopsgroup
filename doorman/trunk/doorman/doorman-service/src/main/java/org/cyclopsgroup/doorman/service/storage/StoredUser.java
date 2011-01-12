package org.cyclopsgroup.doorman.service.storage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * POJO of a user in database
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Entity
@Table( name = "dm_user", uniqueConstraints = { @UniqueConstraint( columnNames = { "user_name" } ),
    @UniqueConstraint( columnNames = { "email_address" } ) } )
@NamedQuery( name = StoredUser.QUERY_BY_NAME_OR_ID, query = "FROM StoredUser WHERE userName = ? OR userId = ?" )
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class StoredUser
{
    /**
     * Name of the query that looks for user based on given user name
     */
    public static final String QUERY_BY_NAME_OR_ID = "findUserByNameOrId";

    private Date creationDate;

    private String displayName;

    private String domainName;

    private String emailAddress;

    private Date lastModified;

    private String password;

    private String timeZoneId;

    private String userId;

    private String userName;

    private StoredUserState userState;

    private StoredUserType userType;

    /**
     * @return Creation date
     */
    @Column( name = "creation_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    public final Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * @return Display name of user
     */
    @Column( name = "display_name", nullable = false, length = 64 )
    public String getDisplayName()
    {
        return displayName;
    }

    /**
     * @return Domain name which indicates where the user was registered in the very beginning
     */
    @Column( name = "domain_name", length = 32, nullable = false )
    public String getDomainName()
    {
        return domainName;
    }

    /**
     * @return Email address of the user
     */
    @Column( name = "email_address", nullable = false, length = 64 )
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @return Last modified timestamp of user
     */
    @Column( name = "last_modified" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getLastModified()
    {
        return lastModified;
    }

    /**
     * @return Password for login
     */
    @Column( name = "password", nullable = false, length = 32 )
    public String getPassword()
    {
        return password;
    }

    /**
     * @return Time zone user lives in
     */
    @Column( name = "time_zone_id", length = 32 )
    public String getTimeZoneId()
    {
        return timeZoneId;
    }

    /**
     * @return Internal user identifier of user
     */
    @Id
    @Column( name = "user_id", nullable = false, length = 32 )
    public String getUserId()
    {
        return userId;
    }

    /**
     * @return Unique name of user
     */
    @Column( name = "user_name", nullable = false, length = 64 )
    public String getUserName()
    {
        return userName;
    }

    /**
     * @return State of user model
     */
    @Column( name = "user_state", nullable = false, length = 8 )
    @Enumerated( EnumType.STRING )
    public StoredUserState getUserState()
    {
        return userState;
    }

    /**
     * @return Type of user
     */
    @Column( name = "user_type", nullable = false, length = 8 )
    @Enumerated( EnumType.STRING )
    public final StoredUserType getUserType()
    {
        return userType;
    }

    /**
     * @param creationDate {@link #getCreationDate()}
     */
    public final void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    /**
     * @param displayName {@link #getDisplayName()}
     */
    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    /**
     * @param domainName {@link #getDomainName()}
     */
    public void setDomainName( String domainName )
    {
        this.domainName = domainName;
    }

    /**
     * @param emailAddress {@link #getEmailAddress()}
     */
    public void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @param lastModified {@link #getLastModified()}
     */
    public void setLastModified( Date lastModified )
    {
        this.lastModified = lastModified;
    }

    /**
     * @param password {@link #getPassword()}
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * @param timeZoneId Time zone user lives in
     */
    public void setTimeZoneId( String timeZoneId )
    {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @param userId {@link #getUserId()}
     */
    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    /**
     * @param userName {@link #getUserName()}
     */
    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    /**
     * @param userState {@link #getUserState()}
     */
    public void setUserState( StoredUserState userState )
    {
        this.userState = userState;
    }

    /**
     * @param userType {@link #getUserType()}
     */
    public final void setUserType( StoredUserType userType )
    {
        this.userType = userType;
    }
}
