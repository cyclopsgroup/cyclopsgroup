package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;

/**
 * Model of a registered user
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "User" )
@XmlType
public class User
{
    private String countryCode;

    private DateTime creationDate;

    private String displayName;

    private String domainName;

    private String emailAddress;

    private String languageCode;

    private DateTime lastVisit;

    private String password;

    private String timeZoneId;

    private String userId;

    private String userName;

    /**
     * @param other Another user object to copy to
     */
    public void copyTo( User other )
    {
        other.displayName = displayName;
        other.domainName = domainName;
        other.emailAddress = emailAddress;
        other.password = password;
        other.timeZoneId = timeZoneId;
        other.userId = userId;
        other.userName = userName;
    }

    /**
     * @return Country code of country user lives
     */
    @XmlElement
    public final String getCountryCode()
    {
        return countryCode;
    }

    /**
     * @return Creation date of user
     */
    @XmlElement
    @XmlJavaTypeAdapter( XmlDateTimeAdapter.class )
    public final DateTime getCreationDate()
    {
        return creationDate;
    }

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
     * @return Language code of language that user prefers
     */
    public final String getLanguageCode()
    {
        return languageCode;
    }

    /**
     * @return Date time of user's last visit
     */
    @XmlElement
    @XmlJavaTypeAdapter( XmlDateTimeAdapter.class )
    public DateTime getLastVisit()
    {
        return lastVisit;
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
     * @param countryCode {@link #getCountryCode()}
     */
    public final void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    /**
     * @param creationDate {@link #getCreationDate()}
     */
    public void setCreationDate( DateTime creationDate )
    {
        this.creationDate = creationDate;
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
     * @param languageCode {@link #getLanguageCode()}
     */
    public final void setLanguageCode( String languageCode )
    {
        this.languageCode = languageCode;
    }

    /**
     * @param lastVisit {@link #getLastVisit()}
     */
    public void setLastVisit( DateTime lastVisit )
    {
        this.lastVisit = lastVisit;
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
