package com.cyclopsgroup.nimbus;

public class ClientNotRegisteredException
    extends ClientException
{
    private static final long serialVersionUID = 1L;

    ClientNotRegisteredException( String serviceClientId )
    {
        super( serviceClientId, "Client is not registered" );
    }
}
