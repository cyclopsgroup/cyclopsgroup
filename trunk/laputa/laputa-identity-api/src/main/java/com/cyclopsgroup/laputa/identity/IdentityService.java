package com.cyclopsgroup.laputa.identity;

public interface IdentityService
{
    public enum AuthenticationResult {
        DISABLED_USER, ERROR, NO_SUCH_USER, SUCCESSFUL, WRONG_PASSWORD;
    }

    AuthenticationResult authenticate( String userName, String password );

    /**
     * @param identityToken Identity token for current user
     * @return Identity object. Null if token is invalid or expired
     */
    Identity getIdentity( String identityToken );

    /**
     * Sign in as an user
     * 
     * @param userName Change current use to be specified one
     * @return A token to identify current user
     */
    String signIn( String userName )
        throws NoSuchUserException;

    /**
     * Sign out given user token
     * 
     * @param subjectToken
     */
    void signOut( String identityToken );
}
