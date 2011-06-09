package org.cyclopsgroup.doorman.api;

/**
 * Interface that allows to inject additional code logic triggered when certain event happens
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface UserEventListener
{
    /**
     * Called when a new user account is requested
     *
     * @param sessionId Session that requests the sign up
     * @param requestedUser Requested user POJO
     */
    void signUpRequested( String sessionId, User requestedUser );
}
