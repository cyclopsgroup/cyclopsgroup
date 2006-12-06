package com.cyclopsgroup.laputa.identity.ws.client;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.IdentityService;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

public class IdentityServiceWebServiceClient
    implements IdentityService
{
    private IdentityServiceStub stub;

    public IdentityServiceWebServiceClient( String serviceEndPoint )
        throws AxisFault
    {
        stub = new IdentityServiceStub( serviceEndPoint );
    }

    public AuthenticationResult authenticate( String userName, String password )
    {
        IdentityServiceStub.Credential c = new IdentityServiceStub.Credential();
        c.setUsername( userName );
        c.setPassword( password );
        try
        {
            return AuthenticationResult.valueOf( stub.authenticate( c ).getValue() );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
            return AuthenticationResult.ERROR;
        }
    }

    public Identity getIdentity( String ticket )
    {
        IdentityServiceStub.Ticket t = new IdentityServiceStub.Ticket();
        t.setTicket( ticket );
        try
        {
            return new IdentityStub( stub.getIdentity( t ) );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
            return null;
        }
    }

    protected void handleRemoteException( RemoteException e )
    {
        throw new RuntimeException( e );
    }

    public String signIn( String userName )
        throws NoSuchUserException
    {
        IdentityServiceStub.Username u = new IdentityServiceStub.Username();
        u.setUsername( userName );
        try
        {
            return stub.signIn( u ).getTicket();
        }
        catch ( RemoteException e )
        {
            if ( e.getCause() instanceof NoSuchUserException )
            {
                throw (NoSuchUserException) e.getCause();
            }
            handleRemoteException( e );
            return null;
        }
    }

    public void signOut( String ticket )
    {
        IdentityServiceStub.Ticket t = new IdentityServiceStub.Ticket();
        t.setTicket( ticket );
        try
        {
            stub.signOut( t );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
        }
    }
}
