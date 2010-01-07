package org.cyclopsgroup.fiar.service;

import java.util.ArrayList;
import java.util.List;

import org.cyclopsgroup.fiar.Game;
import org.cyclopsgroup.fiar.GameBoard;
import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.GameState;
import org.cyclopsgroup.fiar.Move;
import org.cyclopsgroup.fiar.Moves;
import org.cyclopsgroup.fiar.PlayerRole;
import org.cyclopsgroup.fiar.service.pojo.FiarGame;
import org.cyclopsgroup.fiar.service.pojo.FiarGameMove;
import org.cyclopsgroup.fiar.service.pojo.FiarGamePlayer;
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
        GameState state;
        switch ( game.getGameState() )
        {
            case PENDING:
                state = GameState.PENDING;
                break;
            case ONGOING:
                state = GameState.ONGOING;
                break;
            case EXPIRED:
                state = GameState.EXPIRED;
                break;
            case FINISHED:
                state = GameState.FINISHED;
                break;
            default:
                throw new AssertionError( "Unexpected state " + game.getGameState() );
        }
        g.setGameState( state );
        GameBoard board = new GameBoard();
        board.setWidth( game.getWidth() );
        board.setHeight( game.getHeight() );
        g.setBoard( board );
        return g;
    }

    private static Move toMove( FiarGameMove move )
    {
        Move m = new Move();
        m.setMoveTime( move.getMoveDate() );
        m.setPlayerRole( move.getPlayer() == FiarGamePlayer.OFFENSE ? PlayerRole.OFFENSE : PlayerRole.DEFENSE );
        m.setPlayerId( move.getUserId() );
        m.setPreviousVersion( move.getPreviousVersion() );
        m.setVersion( move.getVersion() );
        m.setX( move.getX() );
        m.setY( move.getY() );
        return m;
    }

    private final GameManager manager;

    private final GameStorageService storage;

    private final UserSessionService userService;

    public DefaultGameService( GameStorageService storage, UserSessionService userService )
    {
        this.userService = userService;
        this.storage = storage;
        this.manager = new GameManager();
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

    /**
     * @inheritDoc
     */
    @Override
    public Moves getMoves( String sessionId, String gameId, int fromVersion )
    {
        FiarGame game = storage.loadGame( gameId );
        if ( game == null )
        {
            throw new NoSuchGameException( "Game " + gameId + " doesn't exist" );
        }
        List<Move> list = new ArrayList<Move>();
        int version = 0;
        for ( FiarGameMove m : manager.getMovesFrom( fromVersion, game ) )
        {
            version = Math.max( version, m.getVersion() );
            list.add( toMove( m ) );
        }

        Moves moves = new Moves();
        moves.setGameId( gameId );
        moves.setPreviousVersion( fromVersion );
        moves.setVersion( version );
        moves.setResults( list );
        return moves;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void joinGame( String sessionId, String gameId )
    {
        String userId = userService.getUserOfSession( sessionId );
        FiarGame game = storage.loadGame( gameId );
        if ( game == null )
        {
            throw new NoSuchGameException( "Game " + gameId + " doesn't exist" );
        }
        manager.joinGame( game, userId );
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
        FiarGameMove move = new FiarGameMove();
        move.setMoveDate( new DateTime() );
        move.setPreviousVersion( version );
        move.setUserId( userId );
        move.setX( x );
        move.setY( y );
        manager.addMove( game, move );
        storage.updateGame( game );
        return toMove( move );
    }
}
