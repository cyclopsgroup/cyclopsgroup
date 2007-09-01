package com.cyclopsgroup.nimbus;

public interface Hello
{
    String SERVICE_CLIENT_ID = HelloClient.class.getName();

    String sayHello( String name );
}
