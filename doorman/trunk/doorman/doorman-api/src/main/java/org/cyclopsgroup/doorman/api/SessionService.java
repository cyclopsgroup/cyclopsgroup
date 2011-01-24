package org.cyclopsgroup.doorman.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    @Path( "/{sessionId}/confirm/{token}" )
    UserOperationResult confirmSignUp( @PathParam( "sessionId" ) String sessionId, @PathParam( "token" ) String token );

    /**
     * Get details of current session
     *
     * @param sessionId Id of current session
     * @return Session model
     */
    @GET
    @Path( "/{sessionId}" )
    UserSession getSession( @PathParam( "sessionId" ) String sessionId );

    /**
     * Update existing session
     *
     * @param sessionId ID of session to update
     * @return Current user session
     */
    @POST
    @Path( "/{sessionId}/ping" )
    UserSession pingSession( @PathParam( "sessionId" ) String sessionId );

    /**
     * Create request for new user sign up. Request needs to be confirmed, {@link #confirmSignUp(String, String)},
     * before user is created
     *
     * @param sessionId Current session ID
     * @param user User details
     * @return Sign up operation result
     */
    @POST
    @Path( "/{sessionId}/user/request" )
    UserSignUpResponse requestSignUp( @PathParam( "sessionId" ) String sessionId, User user );

    /**
     * Sign in and associated user with current session
     *
     * @param sessionId Id of current session
     * @param user User to sign in
     * @param password Password for authentication
     * @return Operation result
     */
    @POST
    @Path( "/{sessionId}/user/signin" )
    UserOperationResult signIn( @PathParam( "sessionId" ) String sessionId, @PathParam( "user" ) String user,
                                @FormParam( "password" ) String password );

    /**
     * Sign out from current session
     *
     * @param sessionId Session ID of current session
     * @return Operation result
     */
    @POST
    @Path( "/{sessionId}/user/signout" )
    UserOperationResult signOut( @PathParam( "sessionId" ) String sessionId );

    /**
     * Sign up new user directly with request/confirm process
     *
     * @param sessionId Current user session Id
     * @param user User request to sign up
     * @return Operation result
     */
    @POST
    @Path( "/{sessionId}/user/signup" )
    UserOperationResult signUp( @PathParam( "sessionId" ) String sessionId, User user );

    /**
     * Start a new session with given ID
     *
     * @param sessionId Given session ID to start
     * @param attributes Attributes attached to the new session
     * @return The session it starts
     */
    @PUT
    @Path( "/{sessionId}" )
    UserSession startSession( @PathParam( "sessionId" ) String sessionId, UserSessionAttributes attributes );
}
