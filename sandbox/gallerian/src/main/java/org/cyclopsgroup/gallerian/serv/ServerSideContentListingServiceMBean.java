package org.cyclopsgroup.gallerian.serv;

/**
 * MBean interface exposed by {@link ServerSideContentListingService}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ServerSideContentListingServiceMBean
{
    /**
     * Register a content repository
     * 
     * @param name Name of repository
     * @param uri URI of repository
     */
    void register(String name, String uri);
}
