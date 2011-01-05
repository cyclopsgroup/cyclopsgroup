package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Model of a registered user
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "User" )
@XmlType
public class User
{
    private String displayName;

    private String domainName;

    private String emailAddress;

    private String password;

    private String timeZoneId;

    private String userId;

    private String userName;

    /**
     * @return Display name of user
     */
    @XmlElement
    public final String getDisplayName()
    {
        return displayName;
    }

    /**
     * @return Name of domain this user is associated with
     */
    @XmlElement
    public final String getDomainName()
    {
        return domainName;
    }

    /**
     * @return Email address of user
     */
    @XmlElement
    public final String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @return Password of user
     */
    @XmlElement
    public final String getPassword()
    {
        return password;
    }

    /**
     * @return ID of the time zone user lives in
     */
    @XmlElement
    public final String getTimeZoneId()
    {
        return timeZoneId;
    }

    /**
     * @return Internal unique and consistent identifier of user
     */
    @XmlElement
    public final String getUserId()
    {
        return userId;
    }

    /**
     * @return User name
     */
    @XmlElement
    public final String getUserName()
    {
        return userName;
    }

    /**
     * @param displayName {@link #getDisplayName()}
     */
    public final void setDisplayName( String displayName )
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
    public final void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @param password {@link #getPassword()}
     */
    public final void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * @param timeZoneId Id of the time zone user lives in
     */
    public final void setTimeZoneId( String timeZoneId )
    {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @param userId {@link #getUserId()}
     */
    public final void setUserId( String userId )
    {
        this.userId = userId;
    }

    /**
     * @param userName {@link #getUserName()}
     */
    public final void setUserName( String userName )
    {
        this.userName = userName;
    }
}
