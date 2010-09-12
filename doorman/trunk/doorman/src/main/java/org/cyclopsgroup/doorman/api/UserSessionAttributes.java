package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "UserSessionAttributes" )
@XmlType
public class UserSessionAttributes
{
    private String acceptLanguage;

    private String ipAddress;

    private String userAgent;

    @XmlElement
    public final String getAcceptLanguage()
    {
        return acceptLanguage;
    }

    @XmlElement
    public final String getIpAddress()
    {
        return ipAddress;
    }

    @XmlElement
    public final String getUserAgent()
    {
        return userAgent;
    }

    public final void setAcceptLanguage( String acceptLanguage )
    {
        this.acceptLanguage = acceptLanguage;
    }

    public final void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }

    public final void setUserAgent( String userAgent )
    {
        this.userAgent = userAgent;
    }
}
