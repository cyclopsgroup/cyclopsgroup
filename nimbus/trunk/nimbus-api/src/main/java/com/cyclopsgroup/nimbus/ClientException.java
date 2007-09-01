package com.cyclopsgroup.nimbus;

public class ClientException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    private final String serviceClientId;

    public ClientException( String serviceClientId, String msg )
    {
        super( "Client " + serviceClientId + ":" + msg );
        this.serviceClientId = serviceClientId;
    }

    public ClientException( String serviceClientId, String msg, Throwable e )
    {
        super( "Client " + serviceClientId + ":" + msg, e );
        this.serviceClientId = serviceClientId;
    }

    public String getServiceClientId()
    {
        return serviceClientId;
    }
}
