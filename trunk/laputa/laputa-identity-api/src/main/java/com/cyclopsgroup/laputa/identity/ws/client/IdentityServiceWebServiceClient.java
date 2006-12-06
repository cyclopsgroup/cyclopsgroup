package com.cyclopsgroup.laputa.identity.ws.client;

import java.net.URL;
import java.rmi.RemoteException;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.IdentityService;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

public class IdentityServiceWebServiceClient
    implements IdentityService
{
    private URL serviceEndPoint;

    private IdentityServiceStub serviceStub;

    public IdentityServiceWebServiceClient( URL serviceEndPoint )
    {
        this.serviceEndPoint = serviceEndPoint;
        try
        {
            this.serviceStub = new IdentityServiceStub( serviceEndPoint.toExternalForm() );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
            throw new RuntimeException( e );
        }
    }

    public AuthenticationResult authenticate( String userName, String password )
    {
        try
        {
            IdentityServiceStub.Credential credentialMessage = new IdentityServiceStub.Credential();
            credentialMessage.setUsername( userName );
            credentialMessage.setPassword( password );
            IdentityServiceStub.AuthenticationResult result = serviceStub.authenticate( credentialMessage );
            return AuthenticationResult.valueOf( result.getValue() );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
            return AuthenticationResult.ERROR;
        }
    }

    public Identity getIdentity( String ticket )
    {
        try
        {
            IdentityServiceStub.Ticket ticketMessage = new IdentityServiceStub.Ticket();
            ticketMessage.setTicket( ticket );
            IdentityServiceStub.Identity identityMessage = serviceStub.getIdentity( ticketMessage );
            return new IdentityStub( identityMessage );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
            return null;
        }
    }

    public URL getServiceEndPoint()
    {
        return serviceEndPoint;
    }

    protected void handleRemoteException( Throwable e )
    {
        throw new RuntimeException( e );
    }

    public String signIn( String userName )
        throws NoSuchUserException
    {
        try
        {
            IdentityServiceStub.Username user = new IdentityServiceStub.Username();
            user.setUsername( userName );
            return serviceStub.signIn( user ).getTicket();
        }
        catch ( RemoteException e )
        {
            if ( e.getCause() instanceof NoSuchUserException )
            {
                throw (NoSuchUserException) e.getCause();
            }
            else
            {
                handleRemoteException( e );
                return null;
            }
        }
    }

    public void signOut( String ticket )
    {
        try
        {
            IdentityServiceStub.Ticket ticketMessage = new IdentityServiceStub.Ticket();
            ticketMessage.setTicket( ticket );
            serviceStub.signOut( ticketMessage );
        }
        catch ( RemoteException e )
        {
            handleRemoteException( e );
        }
    }
}
