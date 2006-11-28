package com.cyclopsgroup.laputa.identity.memory;

import java.util.Hashtable;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.IdentityService;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

/**
 * Implementation of IdentityService which manages users in memory 
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class MemoryIdentityService
    implements IdentityService
{
    private Hashtable<String, MemoryIdentity> identities = new Hashtable<String, MemoryIdentity>();

    public void addIdentity( String userName, String password )
    {
        identities.put( userName, new MemoryIdentity( userName, password ) );
    }

    public AuthenticationResult authenticate( String userName, String password )
    {
        MemoryIdentity id = identities.get( userName );
        if ( id == null )
        {
            return AuthenticationResult.NO_SUCH_USER;
        }
        if ( !id.getPassword().equals( password ) )
        {
            return AuthenticationResult.WRONG_PASSWORD;
        }
        return AuthenticationResult.SUCCESSFUL;
    }

    public Identity getIdentity( String ticket )
    {
        return identities.get( ticket );
    }

    public String signIn( String userName )
        throws NoSuchUserException
    {
        MemoryIdentity id = identities.get( userName );
        if ( id == null )
        {
            throw new NoSuchUserException( userName );
        }
        return id.getUserName();
    }

    public void signOut( String identityToken )
    {
        //do nothing
    }
}