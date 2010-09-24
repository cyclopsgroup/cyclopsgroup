package org.cyclopsgroup.doorman.service.storage;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "dm_user" )
public class StoredUser
{
    private String displayName;

    private String emailAddress;

    private String password;

    private String userId;

    private String userName;

    private StoredUserState userState;

    private Timestamp lastModified;

    @Column( name = "last_modified" )
    @Temporal( TemporalType.TIMESTAMP )
    public final Timestamp getLastModified()
    {
        return lastModified;
    }

    public final void setLastModified( Timestamp lastModified )
    {
        this.lastModified = lastModified;
    }

    @Column( name = "display_name", nullable = false, length = 64 )
    public final String getDisplayName()
    {
        return displayName;
    }

    @Column( name = "email_address", nullable = false, length = 256 )
    public final String getEmailAddress()
    {
        return emailAddress;
    }

    @Column( name = "password", nullable = false, length = 32 )
    public final String getPassword()
    {
        return password;
    }

    @Id
    @Column( name = "user_id", nullable = false, length = 32 )
    public final String getUserId()
    {
        return userId;
    }

    @Column( name = "user_name", nullable = false, length = 64 )
    public final String getUserName()
    {
        return userName;
    }

    @Column( name = "user_state", nullable = false, length = 8 )
    @Enumerated( EnumType.STRING )
    public final StoredUserState getUserState()
    {
        return userState;
    }

    public final void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public final void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    public final void setPassword( String password )
    {
        this.password = password;
    }

    public final void setUserId( String userId )
    {
        this.userId = userId;
    }

    public final void setUserName( String userName )
    {
        this.userName = userName;
    }

    public final void setUserState( StoredUserState userState )
    {
        this.userState = userState;
    }
}
