package org.cyclopsgroup.doorman.api;

public interface DoormanService
{
    UserSession getSession( String sessionId );

    boolean hasUser( String userName );

    void signIn( String sessionId, String userName, String password );

    void signOut( String sessionId );

    void signUp( User user, String sessionId );

    UserSession startSession( String sessionId, UserSessionAttributes attributes );
}
