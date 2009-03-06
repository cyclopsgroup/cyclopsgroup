package org.cyclopsgroup.laputa.am.ws;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.laputa.am.AccountManager;
import org.cyclopsgroup.laputa.am.AnonymousSession;
import org.cyclopsgroup.laputa.am.AuthorizationFailureException;
import org.cyclopsgroup.laputa.am.AuthorizedSession;
import org.cyclopsgroup.laputa.am.Session;

/**
 * Internal implementation of account manager service
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class AccountManagerImpl
    implements AccountManager
{
    private static Log LOG = LogFactory.getLog( AccountManager.class );

    /**
     * @inheritDoc
     */
    @Override
    public AuthorizedSession authorize( String arg0, String arg1, String arg2 )
        throws AuthorizationFailureException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Session getSession( String sessionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String ping( String msg )
    {
        LOG.info( "Received ping " + msg );
        return msg;
    }

    /**
     * @inheritDoc
     */
    public AnonymousSession registerSession( String ipAddress, String macAddress )
    {
        AnonymousSession s = new AnonymousSession();
        s.setIpAddress( ipAddress );
        s.setMacAddress( macAddress );
        s.setSessionId( RandomStringUtils.randomAlphabetic( 16 ) );
        return s;
    }
}
