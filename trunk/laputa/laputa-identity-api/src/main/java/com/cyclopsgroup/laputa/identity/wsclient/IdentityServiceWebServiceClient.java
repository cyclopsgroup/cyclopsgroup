package com.cyclopsgroup.laputa.identity.wsclient;

import java.net.URL;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.IdentityService;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

public class IdentityServiceWebServiceClient
    implements IdentityService
{
    private URL serviceEndPoint;

    public IdentityServiceWebServiceClient( URL serviceEndPoint )
    {
        this.serviceEndPoint = serviceEndPoint;
    }

    public AuthenticationResult authenticate( String userName, String password )
    {
        return null;
    }

    public Identity getIdentity( String ticket )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public URL getServiceEndPoint()
    {
        return serviceEndPoint;
    }

    public String signIn( String userName )
        throws NoSuchUserException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void signOut( String ticket )
    {
        // TODO Auto-generated method stub

    }

}
