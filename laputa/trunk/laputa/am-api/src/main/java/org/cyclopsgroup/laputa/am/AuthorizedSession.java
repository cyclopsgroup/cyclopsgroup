package org.cyclopsgroup.laputa.am;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Authorized session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement
public class AuthorizedSession
    extends Session
{
    private Account account;

    /**
     * @return Value of field account
     */
    @XmlElement
    public final Account getAccount()
    {
        return account;
    }

    /**
     * @param account Value of field account to set
     */
    public final void setAccount( Account account )
    {
        this.account = account;
    }
    
    
}
