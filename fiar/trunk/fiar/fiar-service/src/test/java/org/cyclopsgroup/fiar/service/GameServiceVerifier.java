package org.cyclopsgroup.fiar.service;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.fiar.Game;
import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.GameState;
import org.cyclopsgroup.fiar.Moves;
import org.junit.Before;
import org.junit.Test;

public abstract class GameServiceVerifier
{
    private GameService service;

    protected abstract GameService createGameService();

    @Before
    public void setUpService()
    {
        service = createGameService();
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

        Moves moves = service.getMoves( "joe", game.getGameId(), version );
        assertEquals( 1, moves.getResults().size() );

        version = service.getGameVersion( "xuying", game.getGameId() );
        service.makeMove( "xuying", game.getGameId(), version, 1, 1 );
        service.makeMove( "xuying", game.getGameId(), version, 1, 1 );
        moves = service.getMoves( "joe", game.getGameId(), version );
        assertEquals( 1, moves.getResults().size() );

        moves = service.getMoves( "joe", game.getGameId(), oldVersion );
        assertEquals( 2, moves.getResults().size() );
    }
}
