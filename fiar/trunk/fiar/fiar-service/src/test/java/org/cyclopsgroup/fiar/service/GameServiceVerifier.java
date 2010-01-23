package org.cyclopsgroup.fiar.service;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.cyclopsgroup.fiar.Game;
import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.GameState;
import org.cyclopsgroup.fiar.Moves;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class GameServiceVerifier
{
    private GameService service;

    protected abstract GameService createGameService();

    private Map<String, String> gameSessions;

    public final GameService getGameService()
    {
        return service;
    }

    private class Handler
        implements InvocationHandler
    {
        private final GameService service;

        private Handler( GameService service )
        {
            this.service = service;
        }

        @Override
        public Object invoke( Object proxy, Method method, Object[] args )
            throws Throwable
        {
            if ( method.getName().equals( "createGame" ) )
            {
                gameSessions.put( (String) args[1], (String) args[0] );
            }
            return method.invoke( service, args );
        }
    }

    @Before
    public void setUpService()
    {
        gameSessions = new HashMap<String, String>();
        GameService s = createGameService();
        service =
            (GameService) Proxy.newProxyInstance( getClass().getClassLoader(), new Class<?>[] { GameService.class },
                                                  new Handler( s ) );
    }

    @After
    public void tearDownGames()
    {
        for ( Map.Entry<String, String> e : gameSessions.entrySet() )
        {
            service.destroyGame( e.getValue(), e.getKey() );
        }
    }

    @Test( expected = RuntimeException.class )
    public void testMoveBeforeJoin()
    {
        Game game = service.createGame( "jiaqi", "test" );
        int version = service.getGameVersion( "jiaqi", game.getGameId() );
        service.makeMove( "xuying", game.getGameId(), version, 0, 0 );
    }

    @Test
    public void testCreateGame()
    {
        Game game1 = service.createGame( "jiaqi", "test" );
        assertEquals( GameState.PENDING, game1.getGameState() );

        service.joinGame( "xuying", game1.getGameId() );
        Game game2 = service.createGame( "jiaqi", "test" );
        assertEquals( game1.getGameId(), game2.getGameId() );
        assertEquals( GameState.ONGOING, game2.getGameState() );
    }

    @Test
    public void testGetMoves()
    {
        Game game = service.createGame( "jiaqi", "test" );
        service.joinGame( "xuying", game.getGameId() );
        int version = service.getGameVersion( "jiaqi", game.getGameId() );
        int oldVersion = version;
        service.makeMove( "jiaqi", game.getGameId(), version, 0, 0 );

        Moves moves = service.getMoves( game.getGameId(), version );
        assertEquals( 1, moves.getResults().size() );

        version = service.getGameVersion( "xuying", game.getGameId() );
        service.makeMove( "xuying", game.getGameId(), version, 1, 1 );
        service.makeMove( "xuying", game.getGameId(), version, 1, 1 );
        moves = service.getMoves( game.getGameId(), version );
        assertEquals( 1, moves.getResults().size() );

        moves = service.getMoves( game.getGameId(), oldVersion );
        assertEquals( 2, moves.getResults().size() );
    }
}
