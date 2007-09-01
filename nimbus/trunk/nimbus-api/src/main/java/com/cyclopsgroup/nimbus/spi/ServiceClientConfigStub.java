package com.cyclopsgroup.nimbus.spi;

import java.util.Properties;

public class ServiceClientConfigStub
{
    private long duration = 0L;

    private Properties clientProperties;

    public ServiceClientConfigStub( Properties props )
    {
        clientProperties = props;
    }

    public Properties getClientProperties()
    {
        return clientProperties;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration( long duration )
    {
        this.duration = duration;
    }

}
