package org.cyclopsgroup.fiar.service;

import org.cyclopsgroup.fiar.Game;
import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.Move;
import org.cyclopsgroup.fiar.Moves;
import org.cyclopsgroup.fiar.PlayerRole;
import org.cyclopsgroup.fiar.service.pojo.FiarGame;
import org.cyclopsgroup.fiar.service.pojo.FiarGameMove;
import org.cyclopsgroup.fiar.service.pojo.FiarGamePlayer;
import org.cyclopsgroup.fiar.service.pojo.FiarGameState;
import org.joda.time.DateTime;

/**
 * Implementation of {@link GameService}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class DefaultGameService
    implements GameService
{
    private static Game toGame( FiarGame game )
    {
        Game g = new Game();
        g.setCreationTime( game.getCreationDate() );
        g.setCreatorId( game.getCreatorId() );
        g.setGameId( game.getGameId() );
        g.setGameName( game.getGameName() );
        g.setVersion( game.getVersion() );
        return g;
    }

    private static Move toMove( FiarGameMove move, int previousVersion )
    {
        Move m = new Move();
        m.setMoveTime( move.getMoveDate() );
        m.setPlayerRole( move.getPlayer() == FiarGamePlayer.OFFENSE ? PlayerRole.OFFENSE : PlayerRole.DEFENSE );
        m.setVersion( move.getVersion() );
        m.setX( move.getX() );
        m.setY( move.getY() );
        m.setPreviousVersion( previousVersion );
        return m;
    }

    private final FiarGameStorage storage;

    private final UserSessionService userService;

    public DefaultGameService( FiarGameStorage storage, UserSessionService userService )
    {
        this.storage = storage;
        this.userService = userService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Game createGame( String sessionId, String gameName )
    {
        String userId = userService.getUserOfSession( sessionId );
        String gameId = userId + "-" + gameName;
        FiarGame game;
        synchronized ( storage )
        {
            game = storage.loadGame( gameId );
            if ( game != null )
            {
                return toGame( game );
            }
            game = new FiarGame();
            game.setCreationDate( new DateTime() );
            game.setCreatorId( userId );
            game.setOffensePlayerId( userId );
            game.setGameId( gameId );
            game.setGameName( gameName );
            game.setHeight( 11 );
            game.setWidth( 11 );
            storage.storeGame( game );
        }
        return toGame( game );
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getGameVersion( String sessionId, String gameId )
    {
        FiarGame game = storage.loadGame( gameId );
        if ( game == null )
        {
            throw new NoSuchGameException( "Game " + gameId + " doesn't exist" );
        }
        return game.getVersion();
    }

    @Override
    public Moves getMoves( String sessionId, String gameId, int fromVersion )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void joinGame( String sessionId, String gameId )
    {
        String userId = userService.getUserOfSession( sessionId );
        FiarGame game = storage.loadGame( gameId );
        if ( game == null )
        {
            throw new NoSuchGameException( "Game " + gameId + " doesn't exist" );
        }
        synchronized ( game )
        {
            if ( game.getGameState() != FiarGameState.PENDING )
            {
                throw new IllegalStateException( "User can join only when state of game is PENDING. Current state is "
                    + game.getGameState() );
            }
            game.setDefensePlayerId( userId );
            game.setGameState( FiarGameState.ONGOING );
            game.setVersion( game.getVersion() + 1 );
        }
        storage.updateGame( game );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Move makeMove( String sessionId, String gameId, int version, int x, int y )
    {
        String userId = userService.getUserOfSession( sessionId );
        FiarGame game = storage.loadGame( gameId );
        int previousVersion;
        FiarGameMove move;
        synchronized ( game )
        {
            if ( game.getGameState() != FiarGameState.ONGOING )
            {
                throw new IllegalStateException( "Move can only be made when state is ONGING. Current state is "
                    + game.getGameState() );
            }
            if ( !game.getNextMovePlayer().getUserIdOf( game ).equals( userId ) )
            {
                throw new IllegalStateException( "It's not " + userId + "'s turn to make move" );
            }
            previousVersion = game.getVersion();
            move = new FiarGameMove();
            move.setMoveDate( new DateTime() );
            move.setPlayer( game.getNextMovePlayer() );
            move.setUserId( userId );
            move.setX( x );
            move.setY( y );
            game.addMove( move );
        }
        return toMove( move, previousVersion );
    }
}
