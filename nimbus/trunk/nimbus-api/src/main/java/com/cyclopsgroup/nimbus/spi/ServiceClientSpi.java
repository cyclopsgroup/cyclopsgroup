package com.cyclopsgroup.nimbus.spi;

import java.util.Properties;

public interface ServiceClientSpi
{
    Object call( String methodName, Object[] arguments )
        throws Exception;

    void configure( Properties props, boolean reconfiguration );

    String getServiceClientId();
}
