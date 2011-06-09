package org.cyclopsgroup.doorman.api;

/**
 * A base class for event listener that does nothing for each event
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public abstract class AbstractUserEventListener
    implements UserEventListener
{
    /**
     * @inheritDoc
     */
    @Override
    public void signUpRequested( String sessionId, User requestedUser )
    {
    }
}
