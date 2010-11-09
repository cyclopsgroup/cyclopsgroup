package org.cyclopsgroup.doorman.service.storage;

/**
 * Internal state of a user
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum StoredUserState
{
    /**
     * User is active and valid
     */
    ACTIVE,
    /**
     * User is temporarily suspended
     */
    SUSPENDED,
    /**
     * User is gone and not valid
     */
    DISABLED;
}
