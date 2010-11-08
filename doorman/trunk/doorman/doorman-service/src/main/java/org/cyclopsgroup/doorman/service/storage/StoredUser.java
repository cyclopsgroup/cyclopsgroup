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

@Entity
@Table( name = "dm_user", uniqueConstraints = { @UniqueConstraint( columnNames = { "user_name" } ),
    @UniqueConstraint( columnNames = { "email_address" } ) } )
@NamedQuery( name = StoredUser.QUERY_BY_NAME, query = "FROM StoredUser WHERE userName = :userName" )
public class StoredUser
{
    public static final String QUERY_BY_NAME = "findUserByName";

    private String displayName;

    private String domainName;

    private String emailAddress;

    private Date lastModified;

    private String password;

    private String userId;

    private String userName;

    private StoredUserState userState;

    @Column( name = "display_name", nullable = false, length = 64 )
    public String getDisplayName()
    {
        return displayName;
    }

    @Column( name = "domain_name", length = 32 )
    public final String getDomainName()
    {
        return domainName;
    }

    @Column( name = "email_address", nullable = false, length = 256 )
    public String getEmailAddress()
    {
        return emailAddress;
    }

    @Column( name = "last_modified" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getLastModified()
    {
        return lastModified;
    }

    @Column( name = "password", nullable = false, length = 32 )
    public String getPassword()
    {
        return password;
    }

    @Id
    @Column( name = "user_id", nullable = false, length = 32 )
    public String getUserId()
    {
        return userId;
    }

    @Column( name = "user_name", nullable = false, length = 64 )
    public String getUserName()
    {
        return userName;
    }

    @Column( name = "user_state", nullable = false, length = 8 )
    @Enumerated( EnumType.STRING )
    public StoredUserState getUserState()
    {
        return userState;
    }

    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public final void setDomainName( String domainName )
    {
        this.domainName = domainName;
    }

    public void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    public void setLastModified( Date lastModified )
    {
        this.lastModified = lastModified;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public void setUserState( StoredUserState userState )
    {
        this.userState = userState;
    }
}
