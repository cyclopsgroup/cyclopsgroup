package com.cyclopsgroup.nimbus;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.cyclopsgroup.nimbus.spi.ServiceClientConfigStub;

public class RemoteConfiguredClientContext
    extends ClientContext
{
    private final String url;

    public RemoteConfiguredClientContext( String url )
    {
        this.url = url;
    }

    @Override
    protected ServiceClientConfigStub retrieveServiceClientConfig( String serviceClientId )
        throws IOException
    {
        String fullUrl = url.replace( "$RID$", serviceClientId );
        URLConnection con = new URL( fullUrl ).openConnection();
        Properties props = new Properties();
        props.load( con.getInputStream() );
        ServiceClientConfigStub stub = new ServiceClientConfigStub( props );
        return stub;
    }
}
