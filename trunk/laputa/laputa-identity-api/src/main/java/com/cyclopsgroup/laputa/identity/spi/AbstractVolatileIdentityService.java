package com.cyclopsgroup.laputa.identity.spi;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.IdentityService;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

/**
 * Base IdentityService implementation which removes timeout sessions automatically
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public abstract class AbstractVolatileIdentityService
    implements IdentityService
{
    private class Session
    {
        private Identity identity;

        private long lastAccessed;

        private Session( Identity identity )
        {
            this.identity = identity;
            lastAccessed = System.currentTimeMillis();
        }
    }

    private long checkInterval = 1000L * 30;

    private Thread killPhantomSessionsThread;

    private Hashtable<String, Session> sessions = new Hashtable<String, Session>();

    private boolean stopKillingPhantomSessions = false;

    private long timeout = 1000L * 60 * 30;

    public AbstractVolatileIdentityService()
    {
        killPhantomSessionsThread = new Thread()
        {
            @Override
            public void run()
            {
                while ( !stopKillingPhantomSessions )
                {
                    killPhantomSessions();
                    try
                    {
                        Thread.sleep( checkInterval );
                    }
                    catch ( InterruptedException e )
                    {
                        //do nothing, continue to loop
                    }
                }
            }
        };
        killPhantomSessionsThread.start();
    }

    public void close()
    {
        if ( killPhantomSessionsThread == null )
        {
            return;
        }
        stopKillingPhantomSessions = true;
        try
        {
            killPhantomSessionsThread.join();
        }
        catch ( InterruptedException e )
        {
            //do nothing
        }
        finally
        {
            killPhantomSessionsThread = null;
        }
    }

    protected abstract Identity createNewIdentity( String userName )
        throws NoSuchUserException;

    protected String createNewTicket()
    {
        return String.valueOf( System.nanoTime() );
    }

    @Override
    public void finalize()
    {
        close();
    }

    public final long getCheckInterval()
    {
        return checkInterval;
    }

    public Identity getIdentity( String ticket )
    {
        Session session = sessions.get( ticket );
        if ( session == null )
        {
            return null;
        }
        session.lastAccessed = System.currentTimeMillis();
        return session.identity;
    }

    public final long getTimeout()
    {
        return timeout;
    }

    public final void killPhantomSessions()
    {

        Set<String> ticketsToKill = new HashSet<String>();
        for ( String ticket : sessions.keySet() )
        {
            Session session = sessions.get( ticket );
            if ( System.currentTimeMillis() - session.lastAccessed > timeout )
            {
                ticketsToKill.add( ticket );
            }
        }
        for ( String ticket : ticketsToKill )
        {
            removeSession( ticket, true );
        }
    }

    protected void removeSession( String ticket, boolean timeout )
    {
        sessions.remove( ticket );
    }

    public void resetKillPhantomSessionsThread()
    {
        if ( killPhantomSessionsThread != null )
        {
            killPhantomSessionsThread.interrupt();
        }
    }

    public final void setCheckInterval( long checkInteval )
    {
        if ( this.checkInterval != checkInteval )
        {
            this.checkInterval = checkInteval;
            resetKillPhantomSessionsThread();
        }
    }

    public final void setTimeout( long timeout )
    {
        if ( this.timeout != timeout )
        {
            this.timeout = timeout;
            resetKillPhantomSessionsThread();
        }
    }

    public String signIn( String userName )
        throws NoSuchUserException
    {
        Session session = new Session( createNewIdentity( userName ) );
        String ticket = createNewTicket();
        sessions.put( ticket, session );
        return ticket;
    }

    public void signOut( String ticket )
    {
        removeSession( ticket, false );
    }
}