package org.cyclopsgroup.laputa.am.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

/**
 * Client session that remembers every client that has visited system
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Entity
@Table( name = "cg_client_session" )
public class ClientSession
{
    private boolean acitve;

    private boolean authenticated;

    private String idKey;

    private String ipAddress;

    private DateTime lastActiveDate;

    private DateTime lastAuthenticationDate;

    private String sessionId;

    private DateTime startDate;

    private UserAccount userAccount;

    /**
     * @return Value of field idKey
     */
    @Column( name = "id_key", length = 16, nullable = false )
    public final String getIdKey()
    {
        return idKey;
    }

    /**
     * @return Value of field ipAddress
     */
    @Column( name = "ip_address", length = 16 )
    public final String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * @return Value of field lastActiveDate
     */
    public final DateTime getLastActiveDate()
    {
        return lastActiveDate;
    }

    /**
     * @return Value of field lastAuthenticationDate
     */
    public final DateTime getLastAuthenticationDate()
    {
        return lastAuthenticationDate;
    }

    /**
     * @return Value of field sessionId
     */
    @Column( name = "session_id", length = 64 )
    @Id
    public final String getSessionId()
    {
        return sessionId;
    }

    /**
     * @return Value of field startDate
     */
    public final DateTime getStartDate()
    {
        return startDate;
    }

    /**
     * @return Value of field userAccount
     */
    @ManyToOne( optional = false )
    @JoinColumn( name = "user_account_id", nullable = false )
    public final UserAccount getUserAccount()
    {
        return userAccount;
    }

    /**
     * @return Value of field acitve
     */
    @Column( name = "active" )
    public final boolean isAcitve()
    {
        return acitve;
    }

    /**
     * @return Value of field authenticated
     */
    @Column( name = "authenticated" )
    public final boolean isAuthenticated()
    {
        return authenticated;
    }

    /**
     * @param acitve Value of field acitve to set
     */
    public final void setAcitve( boolean acitve )
    {
        this.acitve = acitve;
    }

    /**
     * @param authenticated Value of field authenticated to set
     */
    public final void setAuthenticated( boolean authenticated )
    {
        this.authenticated = authenticated;
    }

    /**
     * @param idKey Value of field idKey to set
     */
    public final void setIdKey( String idKey )
    {
        this.idKey = idKey;
    }

    /**
     * @param ipAddress Value of field ipAddress to set
     */
    public final void setIpAddress( String ipAddress )
    {
        this.ipAddress = ipAddress;
    }

    /**
     * @param lastActiveDate Value of field lastActiveDate to set
     */
    public final void setLastActiveDate( DateTime lastActiveDate )
    {
        this.lastActiveDate = lastActiveDate;
    }

    /**
     * @param lastAuthenticationDate Value of field lastAuthenticationDate to set
     */
    public final void setLastAuthenticationDate( DateTime lastAuthenticationDate )
    {
        this.lastAuthenticationDate = lastAuthenticationDate;
    }

    /**
     * @param sessionId Value of field sessionId to set
     */
    public final void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    /**
     * @param startDate Value of field startDate to set
     */
    public final void setStartDate( DateTime startDate )
    {
        this.startDate = startDate;
    }

    /**
     * @param userAccount Value of field userAccount to set
     */
    public final void setUserAccount( UserAccount userAccount )
    {
        this.userAccount = userAccount;
    }
}
