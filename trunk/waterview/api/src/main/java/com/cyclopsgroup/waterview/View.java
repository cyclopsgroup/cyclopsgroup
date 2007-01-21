package com.cyclopsgroup.waterview;

import java.io.IOException;

import org.apache.avalon.framework.service.ServiceManager;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class View
{
    private ServiceManager serviceManager;

    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    public abstract void render( RunData data, ViewContext viewContext )
        throws IOException;

    public void setServiceManager( ServiceManager serviceManager )
    {
        this.serviceManager = serviceManager;
    }
}
