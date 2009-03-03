package org.cyclopsgroup.laputa.am.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User account that system recognizes
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Entity
@Table( name = "cg_user_account" )
public class UserAccount
{
    private String accountId;

    private UserAccountType accountType;

    private String displayName;

    private String emailAddress;

    /**
     * @return Value of field accountId
     */
    @Column( name = "account_id", length = 64 )
    @Id
    public final String getAccountId()
    {
        return accountId;
    }

    /**
     * @return Value of field accountType
     */
    @Column( name = "account_type", length = 16, nullable = false )
    @Enumerated( EnumType.STRING )
    public final UserAccountType getAccountType()
    {
        return accountType;
    }

    /**
     * @return Value of field displayName
     */
    @Column( name = "display_name", length = 64 )
    public final String getDisplayName()
    {
        return displayName;
    }

    /**
     * @return Value of field emailAddress
     */
    @Column( name = "email_address", length = 256 )
    public final String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @param accountId Value of field accountId to set
     */
    public final void setAccountId( String accountId )
    {
        this.accountId = accountId;
    }

    /**
     * @param accountType Value of field accountType to set
     */
    public final void setAccountType( UserAccountType accountType )
    {
        this.accountType = accountType;
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
}
