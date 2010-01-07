package org.cyclopsgroup.fiar.service.mem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.cyclopsgroup.fiar.service.GameStorageService;
import org.cyclopsgroup.fiar.service.pojo.FiarGame;

/**
 * Storage implementation based on {@link HashMap}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class HashMapGameStorageService
    implements GameStorageService
{
    private final Map<String, FiarGame> games = new ConcurrentHashMap<String, FiarGame>();

    /**
     * @inheritDoc
     */
    @Override
    public boolean deleteGame( String gameId )
    {
        return games.remove( gameId ) != null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FiarGame loadGame( String gameId )
    {
        return games.get( gameId );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void storeGame( FiarGame game )
    {
        games.put( game.getGameId(), game );
    }

    @Override
    public void updateGame( FiarGame game )
    {
    }
}
