package org.cyclopsgroup.doorman.service.core;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.doorman.api.AbstractUserEventListener;
import org.cyclopsgroup.doorman.api.UserEventListener;

/**
 * Real time configuration for user and session service implementations
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SimpleUserSessionConfig
    implements UserSessionConfig, SimpleUserSessionConfigMBean
{
    private static final String DEFAULT_DOMAIN_NAME = "default";

    private static final UserEventListener NO_OP_LISTENER = new AbstractUserEventListener()
    {
    };

    private String domainName = DEFAULT_DOMAIN_NAME;

    private UserEventListener listener = NO_OP_LISTENER;

    /**
     * @inheritDoc
     */
    @Override
    public final String getDomainName()
    {
        return domainName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final UserEventListener getListener()
    {
        return listener;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final void setDomainName( String domainName )
    {
        Validate.notNull( domainName, "Domain name can't be NULL" );
        this.domainName = domainName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final void setListener( UserEventListener listener )
    {
        Validate.notNull( listener, "Listener can't be NULL" );
        this.listener = listener;
    }
}
