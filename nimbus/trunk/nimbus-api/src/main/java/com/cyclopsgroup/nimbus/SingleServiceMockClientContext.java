package com.cyclopsgroup.nimbus;

import java.util.Properties;

import com.cyclopsgroup.nimbus.spi.ServiceClientConfigStub;

public class SingleServiceMockClientContext
    extends ClientContext
{
    private final Properties properties;

    public SingleServiceMockClientContext( Properties properties )
    {
        this.properties = properties;
    }

    @Override
    protected ServiceClientConfigStub retrieveServiceClientConfig( String serviceClientId )
        throws Exception
    {
        return new ServiceClientConfigStub( properties );
    }
}
