package org.cyclopsgroup.laputa.am.hibernate;

import org.cyclopsgroup.laputa.am.bean.ClientSession;
import org.cyclopsgroup.laputa.am.bean.UserAccount;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class AccountManagerConfigUtils
{
    private AccountManagerConfigUtils()
    {
    }

    /**
     * @param config Configuration to add annotated classes to
     */
    public static void addEntities( AnnotationConfiguration config )
    {
        config.addAnnotatedClass( ClientSession.class );
        config.addAnnotatedClass( UserAccount.class );
    }
}
