package org.cyclopsgroup.doorman.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "session" )
public interface SessionService
{
    @GET
    @Path( "/{sessionId}" )
    @Produces( "application/xml" )
    UserSession getSession( @PathParam( "sessionId" ) String sessionId );

    @POST
    @Path( "/{sessionId}/signin" )
    void signIn( @PathParam( "sessionId" ) String sessionId, @FormParam( "user" ) String user,
                 @FormParam( "password" ) String password );

    @POST
    @Path( "/{sessionId}/signout" )
    void signOut( @PathParam( "sessionId" ) String sessionId );

    @POST
    @Path( "/{sessionId}/signup" )
    void signUp( String sessionId, User user );

    @PUT
    @Path( "/{sessionId}" )
    @Produces( "application/xml" )
    UserSession startSession( @PathParam( "sessionId" ) String sessionId, UserSessionAttributes attributes );
}
