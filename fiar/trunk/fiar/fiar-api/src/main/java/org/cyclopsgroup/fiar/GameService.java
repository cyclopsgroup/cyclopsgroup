package org.cyclopsgroup.fiar;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * API exposed for player to play game
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Path( "/" )
public interface GameService
{
    /**
     * @param sessionId Id of current session
     * @param requestDate
     * @return The game user creates
     */
    @PUT
    @Path( "/games/{gameId}" )
    @Produces( "text/xml" )
    Game createGame( @QueryParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId );

    /**
     * Abort and remove game
     *
     * @param sessionId Id of current session
     * @param gameId Id of game to play
     * @return True if game existed
     */
    @DELETE
    @Path( "/games/{gameId}" )
    @Produces( "text/plain" )
    boolean destroyGame( @QueryParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId );

    /**
     * Get the latest version of a game
     *
     * @param sessionId Id of current session
     * @param gameId Id of the game
     * @return Version of the game
     */
    @GET
    @Path( "/games/{gameId}/version" )
    @Produces( "text/plain" )
    int getGameVersion( @PathParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId );

    /**
     * Get list of moves since given version
     *
     * @param sessionId Id of the current session
     * @param gameId Id of the game
     * @param fromVersion Return moves after this version
     * @return List of moves
     */
    @GET
    @Path( "/{gameId}/moves" )
    @Produces( "text/xml" )
    Moves getMoves( @PathParam( "gameId" ) String gameId, @DefaultValue( "0" ) @QueryParam( "version" ) int fromVersion );

    /**
     * Join an open game
     *
     * @param sessionId Id of current session
     * @param gameId Id of the game to join
     * @return The version of join operation
     */
    @POST
    @Path( "/{gameId}/join" )
    @Produces( "text/plain" )
    int joinGame( @QueryParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId );

    /**
     * Make a move in game
     *
     * @param sessionId Id of current session
     * @param gameId Id of game to play
     * @param version The version before move
     * @param x Horizontal coordinate of move
     * @param y Vertical coordinate of move
     * @return Result of this move
     */
    @POST
    @Path( "/{gameId}/move" )
    @Produces( "text/xml" )
    Move makeMove( @QueryParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId,
                   @QueryParam( "version" ) int version, @QueryParam( "x" ) int x, @QueryParam( "y" ) int y );
}
