package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "User" )
@XmlType
public class User
{
    private String displayName;

    private String emailAddress;

    private String userId;

    private String userName;

    @XmlElement
    public final String getDisplayName()
    {
        return displayName;
    }

    @XmlElement
    public final String getEmailAddress()
    {
        return emailAddress;
    }

    @XmlElement
    public final String getUserId()
    {
        return userId;
    }

    @XmlElement
    public final String getUserName()
    {
        return userName;
    }

    public final void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public final void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    public final void setUserId( String userId )
    {
        this.userId = userId;
    }

    public final void setUserName( String userName )
    {
        this.userName = userName;
    }
}
