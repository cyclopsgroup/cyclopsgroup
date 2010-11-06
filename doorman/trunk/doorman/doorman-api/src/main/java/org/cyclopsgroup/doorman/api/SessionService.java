package org.cyclopsgroup.doorman.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * The facade service that manages user authentication, session management and user management
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Path( "session" )
public interface SessionService
{
    /**
     * Confirm a sign up request with given token
     *
     * @param sessionId Current session ID
     * @param token Token to confirm
     * @return Opeation result
     */
    @POST
    @Path( "/{sessionId}/confirm" )
    UserOperationResult confirmSignUp( String sessionId, String token );

    /**
     * Get details of current session
     *
     * @param sessionId Id of current session
     * @return Session model
     */
    @GET
    @Path( "/{sessionId}" )
    @Produces( "application/xml" )
    UserSession getSession( @PathParam( "sessionId" ) String sessionId );

    /**
     * Sign in and associated user with current session
     *
     * @param sessionId Id of current session
     * @param user User to sign in
     * @param password Password for authentication
     * @return Operation result
     */
    @POST
    @Path( "/{sessionId}/signin" )
    UserOperationResult signIn( @PathParam( "sessionId" ) String sessionId, @FormParam( "user" ) String user,
                                @FormParam( "password" ) String password );

    /**
     * Sign out from current session
     *
     * @param sessionId Session ID of current session
     * @return Operation result
     */
    @POST
    @Path( "/{sessionId}/signout" )
    UserOperationResult signOut( @PathParam( "sessionId" ) String sessionId );

    /**
     * Sign up a new user
     *
     * @param sessionId Current session ID
     * @param user User details
     * @return Sign up operation result
     */
    @POST
    @Path( "/{sessionId}/signup" )
    UserOperationResult signUp( String sessionId, User user );

    /**
     * Start a new session with given ID
     *
     * @param sessionId Given session ID to start
     * @param attributes Attributes attached to the new session
     * @return The session it starts
     */
    @PUT
    @Path( "/{sessionId}" )
    @Produces( "application/xml" )
    UserSession startSession( @PathParam( "sessionId" ) String sessionId, UserSessionAttributes attributes );
}
