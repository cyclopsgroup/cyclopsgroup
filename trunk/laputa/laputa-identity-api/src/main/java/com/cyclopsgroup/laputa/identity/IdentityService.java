package com.cyclopsgroup.laputa.identity;

public interface IdentityService
{
    public enum AuthenticationResult {
        DISABLED_USER, ERROR, NO_SUCH_USER, SUCCESSFUL, WRONG_PASSWORD;
    }

    AuthenticationResult authenticate( String userName, String password );

    /**
     * @param ticket Identity token for current user
     * @return Identity object. Null if token is invalid or expired
     */
    Identity getIdentity( String ticket );

    /**
     * Sign in as an user
     * 
     * @param userName Change current use to be specified one
     * @return Ticket associated with login session
     */
    String signIn( String userName )
        throws NoSuchUserException;

    /**
     * Sign out given user token
     * 
     * @param ticket Ticket to sign out
     */
    void signOut( String ticket );
}
