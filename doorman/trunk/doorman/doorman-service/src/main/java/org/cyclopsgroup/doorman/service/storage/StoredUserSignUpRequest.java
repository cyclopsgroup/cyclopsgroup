package org.cyclopsgroup.doorman.service.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.cyclopsgroup.service.security.PasswordStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * Table that stored sign up request that has not been finished yet
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Entity
@Table( name = "dm_signup_request", uniqueConstraints = { @UniqueConstraint( columnNames = { "request_token" } ) } )
@NamedQuery( name = StoredUserSignUpRequest.QUERY_BY_TOKEN, query = "FROM StoredUserSignUpRequest WHERE requestToken = ?" )
public class StoredUserSignUpRequest
{
    /**
     * Name of the query that looks for request based on token
     */
    public static final String QUERY_BY_TOKEN = "findUserSignupRequestByToken";

    private String displayName;

    private String domainName;

    private String emailAddress;

    private String password;

    private PasswordStrategy passwordStrategy;

    private DateTime requestDate;

    private String requestId;

    private String requestToken;

    private String timeZoneId;

    private String userName;

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
    public final String getDomainName()
    {
        return domainName;
    }

    /**
     * @return Email address of user
     */
    @Column( name = "email_address", nullable = false, length = 256 )
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @return Password of new user
     */
    @Column( name = "password", nullable = false, length = 32 )
    public String getPassword()
    {
        return password;
    }

    /**
     * @return The way password is calculated
     */
    @Column( name = "password_strategy", length = 8, nullable = false )
    @Enumerated( EnumType.STRING )
    public PasswordStrategy getPasswordStrategy()
    {
        return passwordStrategy;
    }

    /**
     * @return Timestamp of request
     */
    @Column( name = "request_date", nullable = false )
    @Type( type = "datetime" )
    public DateTime getRequestDate()
    {
        return requestDate;
    }

    /**
     * @return Unique identifier of signup request
     */
    @Id
    @Column( name = "request_id", length = 32 )
    public String getRequestId()
    {
        return requestId;
    }

    /**
     * @return A secret token to finish sign up process
     */
    @Column( name = "request_token", nullable = false, length = 64 )
    public String getRequestToken()
    {
        return requestToken;
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
     * @return Name of user to sign up
     */
    @Column( name = "user_name", nullable = false, length = 64 )
    public String getUserName()
    {
        return userName;
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
    public final void setDomainName( String domainName )
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
     * @param password {@link #getPassword()}
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * @param passwordStrategy {@link #getPasswordStrategy()}
     */
    public void setPasswordStrategy( PasswordStrategy passwordStrategy )
    {
        this.passwordStrategy = passwordStrategy;
    }

    /**
     * @param requestDate {@link #getRequestDate()}
     */
    public void setRequestDate( DateTime requestDate )
    {
        this.requestDate = requestDate;
    }

    /**
     * @param requestId {@link #getRequestId()}
     */
    public void setRequestId( String requestId )
    {
        this.requestId = requestId;
    }

    /**
     * @param requestToken {@link #getRequestToken()}
     */
    public void setRequestToken( String requestToken )
    {
        this.requestToken = requestToken;
    }

    /**
     * @param timeZoneId Time zone user lives in
     */
    public void setTimeZoneId( String timeZoneId )
    {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @param userName {@link #getUserName()}
     */
    public void setUserName( String userName )
    {
        this.userName = userName;
    }
}
