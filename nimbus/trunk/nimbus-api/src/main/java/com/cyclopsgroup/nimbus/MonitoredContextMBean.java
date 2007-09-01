package com.cyclopsgroup.nimbus;

import java.util.Date;
import java.util.Properties;

public interface MonitoredContextMBean
{
    void forceLoad( String serviceClientId )
        throws ClientException;

    String[] getClientIds();

    Properties getClientProperties( String serviceClientId );

    boolean isClientConfigured( String serviceClientId );

    void register( String clientClassName )
        throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    void reset();

    void reset( String serviceClientId );

    boolean setClientExpiration( String serviceClientId, Date expiration );

    boolean setClientExpiration( String serviceClientId, long expiration );
}
