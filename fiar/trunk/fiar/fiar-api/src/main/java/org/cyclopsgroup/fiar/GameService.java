package org.cyclopsgroup.fiar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * API exposed for player to play game
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Path( "/game" )
public interface GameService
{
    /**
     * @param sessionId Id of current session
     * @param requestDate
     * @return The game user creates
     */
    @GET
    @Path( "/{sessionId}/create/{name}" )
    @Produces( "text/xml" )
    Game createGame( @PathParam( "sessionId" ) String sessionId, @PathParam( "name" ) String gameName );

    /**
     * Get the latest version of a game
     *
     * @param sessionId Id of current session
     * @param gameId Id of the game
     * @return Version of the game
     */
    @GET
    @Path( "/{sessionId}/{gameId}/version" )
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
    @Path( "/{sessionId}/{gameId}/moves" )
    @Produces( "text/xml" )
    Moves getMoves( @PathParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId,
                          int fromVersion );

    /**
     * Join an open game
     *
     * @param sessionId Id of current session
     * @param gameId Id of the game to join
     */
    @GET
    @Path( "/{sessionId}/{gameId}/join" )
    @Produces( "text/plain" )
    void joinGame( @PathParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId );

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
    @GET
    @Path( "/{sessionId}/{gameId}/move" )
    @Produces( "text/xml" )
    Move makeMove( @PathParam( "sessionId" ) String sessionId, @PathParam( "gameId" ) String gameId,
                         @QueryParam( "version" ) int version, @QueryParam( "x" ) int x, @QueryParam( "y" ) int y );
}
