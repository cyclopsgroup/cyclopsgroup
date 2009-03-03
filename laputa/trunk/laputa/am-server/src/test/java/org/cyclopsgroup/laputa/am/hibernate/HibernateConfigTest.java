package org.cyclopsgroup.laputa.am.hibernate;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.laputa.am.bean.UserAccount;
import org.cyclopsgroup.laputa.am.bean.UserAccountType;
import org.junit.Test;

/**
 * A test case that does over validation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class HibernateConfigTest
    extends PersistentJunitSupport
{
    /**
     * Verify configuration is intact
     */
    @Test
    public void testCreation()
    {
        UserAccount account = new UserAccount();
        account.setAccountId( "aid" );
        account.setDisplayName( "Hello" );
        account.setAccountType( UserAccountType.CUSTOMER );

        aSession().save( account );
        aSession().flush();
        UserAccount sameAccount = (UserAccount) aSession().load( UserAccount.class, "aid" );
        assertEquals( "Hello", sameAccount.getDisplayName() );
    }
}
