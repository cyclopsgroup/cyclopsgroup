package org.cyclopsgroup.fiar.service.gae;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.fiar.service.GameStorageService;
import org.cyclopsgroup.fiar.service.pojo.FiarGame;

/**
 * Implementation of storage using JCache
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class JCacheGameStorageService
    implements GameStorageService
{
    private final Cache gameCache;

    /**
     * @throws CacheException If cache can't be created
     */
    public JCacheGameStorageService()
        throws CacheException
    {
        gameCache = CacheManager.getInstance().getCacheFactory().createCache( Collections.emptyMap() );
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean deleteGame( String gameId )
    {
        return gameCache.remove( gameId ) != null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FiarGame loadGame( String gameId )
    {
        Validate.notNull( gameId, "Game ID can't be NULL" );
        return (FiarGame) gameCache.get( "game:" + gameId );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void storeGame( FiarGame game )
    {
        gameCache.put( "game:" + game.getGameId(), game );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateGame( FiarGame game )
    {
        storeGame( game );
    }
}
