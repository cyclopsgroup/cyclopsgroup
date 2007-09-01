package com.cyclopsgroup.nimbus;

import java.util.Date;
import java.util.Properties;

import com.cyclopsgroup.nimbus.spi.ServiceClientConfigStub;
import com.cyclopsgroup.nimbus.spi.ServiceClientSpi;

public class MonitoredContext
    implements MonitoredContextMBean
{
    private static final String[] EMPTY_STRING_ARRAY = {};

    private final ClientContext clientContext;

    public MonitoredContext( ClientContext clientContext )
    {
        this.clientContext = clientContext;
    }

    public void forceLoad( String serviceClientId )
        throws ClientException
    {
        clientContext.forceLoadConfiguration( serviceClientId );
    }


    public String[] getClientIds()
    {
        return clientContext.clients.keySet().toArray( EMPTY_STRING_ARRAY );
    }

    public Properties getClientProperties( String serviceClientId )
    {
        ServiceClientConfigStub config = clientContext.configStubs.get( serviceClientId );
        return config == null ? null : config.getClientProperties();
    }

    public boolean isClientConfigured( String serviceClientId )
    {
        ServiceClientStub client = clientContext.clients.get( serviceClientId );
        return client == null ? false : client.isConfigured();
    }

    public void register( String clientClassName )
        throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        clientContext.registerService( (ServiceClientSpi) Class.forName( clientClassName ).newInstance() );
    }

    public void reset()
    {
        clientContext.reset();
    }

    public void reset( String serviceClientId )
    {
        clientContext.reset( serviceClientId );
    }

    public boolean setClientExpiration( String serviceClientId, Date expiration )
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setClientExpiration( String serviceClientId, long expiration )
    {
        return false;
        // TODO Auto-generated method stub
    }
}
