package org.cyclopsgroup.doorman.service.core;

import org.cyclopsgroup.doorman.api.UserEventListener;

/**
 * Real time configuration used by user and session service
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface UserSessionConfig
{
    /**
     * @return Service domain name
     */
    String getDomainName();

    /**
     * @return User session listener
     */
    UserEventListener getListener();
}
