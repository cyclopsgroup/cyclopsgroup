package org.cyclopsgroup.doorman.service.core;

import org.cyclopsgroup.doorman.api.UserEventListener;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface SimpleUserSessionConfigMBean
    extends UserSessionConfig
{
    /**
     * @param domainName Domain name of service
     */
    void setDomainName( String domainName );

    /**
     * @param listener User session listeners
     */
    void setListener( UserEventListener listener );
}
