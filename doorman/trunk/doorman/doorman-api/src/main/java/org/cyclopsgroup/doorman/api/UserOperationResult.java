package org.cyclopsgroup.doorman.api;

/**
 * Enumeration of possible user and session operation results
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum UserOperationResult
{
    /**
     * Operation is successful
     */
    SUCCESSFUL,
    /**
     * Operation failed because identity can't be authenticated correctly
     */
    AUTHENTICATION_FAILURE,
    /**
     * Usually for signing up, the desired identity already exists
     */
    IDENTITY_EXISTED,
    /**
     * Expected identity doesn't exist
     */
    NO_SUCH_IDENTITY;
}
