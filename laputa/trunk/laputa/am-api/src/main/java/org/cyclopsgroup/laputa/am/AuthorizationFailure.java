package org.cyclopsgroup.laputa.am;

/**
 * Enumeration of authorization failures
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public enum AuthorizationFailure
{
    /**
     * Password isn't right
     */
    INVALID_PASSWORD,
    /**
     * Invalid session ID was provided
     */
    INVALID_SESSION, 
    /**
     * User name is unknown
     */
    UNKOWN_USER, 
    /**
     * User is disabled by server
     */
    USER_IN_BLACKLIST;
}
