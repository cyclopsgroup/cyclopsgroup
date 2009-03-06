package org.cyclopsgroup.laputa.am;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean for user account
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement
public class Account
{
    private String accountId;

    private String displayName;

    private String emailAddress;

    private String userName;

    /**
     * @return Value of field accountId
     */
    @XmlElement
    public final String getAccountId()
    {
        return accountId;
    }
    
    /**
     * @return Value of field displayName
     */
    @XmlElement
    public final String getDisplayName()
    {
        return displayName;
    }

    /**
     * @return Value of field emailAddress
     */
    @XmlElement
    public final String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @return Value of field userName
     */
    @XmlElement
    public final String getUserName()
    {
        return userName;
    }

    /**
     * @param accountId Value of field accountId to set
     */
    public final void setAccountId( String accountId )
    {
        this.accountId = accountId;
    }

    /**
     * @param displayName Value of field displayName to set
     */
    public final void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    /**
     * @param emailAddress Value of field emailAddress to set
     */
    public final void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @param userName Value of field userName to set
     */
    public final void setUserName( String userName )
    {
        this.userName = userName;
    }
}
