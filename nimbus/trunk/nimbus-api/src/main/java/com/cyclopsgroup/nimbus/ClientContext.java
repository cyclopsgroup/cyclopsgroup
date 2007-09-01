package com.cyclopsgroup.nimbus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cyclopsgroup.nimbus.spi.ServiceClientConfigStub;
import com.cyclopsgroup.nimbus.spi.ServiceClientSpi;

public abstract class ClientContext
{
    final Map<String, ServiceClientStub> clients = new ConcurrentHashMap<String, ServiceClientStub>();

    final Map<String, ServiceClientConfigStub> configStubs = new ConcurrentHashMap<String, ServiceClientConfigStub>();

    public Object call( String serviceClientId, String methodName, Object... objects )
        throws ClientException
    {
        ServiceClientStub client = clients.get( serviceClientId );
        if ( client == null )
        {
            throw new ClientNotRegisteredException( serviceClientId );
        }
        ServiceClientConfigStub configStub = configStubs.get( serviceClientId );
        if ( System.currentTimeMillis() > client.getExpiration() )
        {
            configStub = null;
            configStubs.remove( serviceClientId );
        }
        boolean requireConfiguration = false;
        synchronized ( this )
        {
            configStub = configStubs.get( serviceClientId );
            if ( configStub == null )
            {
                configStub = retrieveConfigInternally( serviceClientId );
                configStubs.put( serviceClientId, configStub );
                requireConfiguration = true;
            }
        }
        ServiceClientSpi clientSpi = client.getClient();
        if ( requireConfiguration )
        {
            synchronized ( client )
            {
                clientSpi.configure( configStub.getClientProperties(), client.isConfigured() );
                client.setConfigured( true );
            }
        }
        try
        {
            return clientSpi.call( methodName, objects );
        }
        catch ( Exception e )
        {
            throw new NestedClientException( serviceClientId, e );
        }
    }

    public void forceLoadConfiguration( String serviceClientId )
        throws ClientException
    {
        ServiceClientStub client = clients.get( serviceClientId );
        if ( client == null )
        {
            throw new ClientNotRegisteredException( serviceClientId );
        }
        ServiceClientConfigStub configStub = retrieveConfigInternally( serviceClientId );
        synchronized ( client )
        {
            client.getClient().configure( configStub.getClientProperties(), client.isConfigured() );
            client.setConfigured( true );
        }
    }

    public void registerService( ServiceClientSpi client )
    {
        clients.put( client.getServiceClientId(), new ServiceClientStub( client ) );
    }

    public void reset()
    {
        configStubs.clear();
    }

    public void reset( String serviceClientId )
    {
        configStubs.remove( serviceClientId );
    }

    private synchronized ServiceClientConfigStub retrieveConfigInternally( String serviceClientId )
        throws ClientException
    {
        try
        {
            ServiceClientConfigStub clientConfigStub = retrieveServiceClientConfig( serviceClientId );
            ServiceClientStub client = clients.get( serviceClientId );
            client.setExpiration( System.currentTimeMillis() + clientConfigStub.getDuration() );
            return clientConfigStub;
        }
        catch ( ClientException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new NestedClientException( serviceClientId, e );
        }
    }

    protected abstract ServiceClientConfigStub retrieveServiceClientConfig( String serviceClientId )
        throws Exception;

    public Object createClientProxy( final String serviceClientId, Class<?> serviceInterface )
    {
        InvocationHandler handler = new InvocationHandler()
        {
            public Object invoke( Object proxy, Method method, Object[] args )
                throws Throwable
            {
                return ClientContext.this.call( serviceClientId, method.getName(), args );
            }
        };
        return Proxy.newProxyInstance( Thread.currentThread().getContextClassLoader(),
                                       new Class<?>[] { serviceInterface }, handler );
    }
}
