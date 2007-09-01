package com.cyclopsgroup.nimbus;

import com.cyclopsgroup.nimbus.spi.ServiceClientSpi;

class ServiceClientStub
{
    private final ServiceClientSpi client;

    private boolean configured;

    private long expiration;

    public long getExpiration()
    {
        return expiration;
    }

    public void setExpiration( long expiration )
    {
        this.expiration = expiration;
    }

    ServiceClientStub( ServiceClientSpi client )
    {
        this.client = client;
    }

    public ServiceClientSpi getClient()
    {
        return client;
    }

    public boolean isConfigured()
    {
        return configured;
    }

    public void setConfigured( boolean configured )
    {
        this.configured = configured;
    }
}
