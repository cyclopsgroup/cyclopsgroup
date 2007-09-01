package com.cyclopsgroup.nimbus;

import java.util.Properties;

import com.cyclopsgroup.nimbus.spi.DynamicServiceClient;

public class HelloClient
    extends DynamicServiceClient
    implements Hello
{
    public String sayHello( String name )
    {
        return "Hello, " + name;
    }

    public void configure( Properties props, boolean reconfiguration )
    {
    }

    public String getServiceClientId()
    {
        return HelloClient.class.getName();
    }
}
