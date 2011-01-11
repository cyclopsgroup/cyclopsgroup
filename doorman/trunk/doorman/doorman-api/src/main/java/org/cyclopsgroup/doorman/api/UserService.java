package org.cyclopsgroup.doorman.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Service facade for user manipulation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Path( "user" )
public interface UserService
{
    /**
     * Get user based on ID
     *
     * @param userName Login name of user to get
     * @return User object. When User is not found, 404 should be returned
     */
    @GET
    @Path( "/{userName}" )
    User getUser( @PathParam( "userName" ) String userName );

    /**
     * Authenticate user with given credential. The meaning of credential is a protocol between caller and service,
     * which this interface doesn't know.
     *
     * @param userName Login name of user to authenticate
     * @param secureCredential Credential to authenticate based on
     * @return Authentication result
     */
    @POST
    @Path( "/{userName}/authenticate" )
    UserOperationResult authenticate( String userName, String secureCredential );

    /**
     * A light weight operation to check the status of user
     *
     * @param userName Name of user to check
     * @return Ping result
     */
    @GET
    @Path( "/{userName}/ping" )
    UserOperationResult ping( @PathParam( "userName" ) String userName );
}
