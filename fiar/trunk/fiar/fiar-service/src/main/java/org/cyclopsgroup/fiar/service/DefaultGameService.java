package org.cyclopsgroup.fiar.service;

import org.cyclopsgroup.fiar.Game;
import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.MoveResult;
import org.cyclopsgroup.fiar.MoveResults;
import org.joda.time.DateTime;

/**
 * Implementation of {@link GameService}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class DefaultGameService
    implements GameService
{
    private final FiarGameStorage storage;

    private final UserService userService;

    public DefaultGameService( FiarGameStorage storage, UserService userService )
    {
        this.storage = storage;
        this.userService = userService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Game createGame( String sessionId, DateTime requestDate )
    {
        String userId = userService.getUserOfSession( sessionId );

        Game game = new Game();
        game.setCreatorId( userId );
        game.setCreationTime( requestDate );

        return game;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getGameVersion( String sessionId, String gameId )
    {
        FiarGame game = storage.loadGame( gameId );
        return game.getVersion();
    }

    @Override
    public MoveResults getMoves( String sessionId, String gameId, int fromVersion )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void joinGame( String sessionId, String gameId )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public MoveResult makeMove( String sessionId, String gameId, int version, int x, int y )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
