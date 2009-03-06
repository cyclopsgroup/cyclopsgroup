package org.cyclopsgroup.laputa.am;

import javax.xml.bind.annotation.XmlElement;


/**
 * Bean that identifies client session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class Session
{
    private String ipAddress;

    private String macAddress;

    private String sessionId;

    /**
     * @return Value of field ipAddress
     */
    @XmlElement
    public final String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * @return Value of field macAddress
     */
    @XmlElement
    public final String getMacAddress()
    {
        return macAddress;
    }

    /**
     * @return Value of field sessionId
     */
    @XmlElement
    public final String getSessionId()
    {
        return sessionId;
    }

    /**
     * @param ipAddress Value of field ipAddress to set
     */
    public final void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }
    
    /**
     * @param macAddress Value of field macAddress to set
     */
    public final void setMacAddress( String macAddress )
    {
        this.macAddress = macAddress;
    }
    /**
     * @param sessionId Value of field sessionId to set
     */
    public final void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }
}
