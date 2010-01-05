package org.cyclopsgroup.fiar.service.gae;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.fiar.service.FiarGameStorage;
import org.cyclopsgroup.fiar.service.pojo.FiarGame;

/**
 * Implementation of storage using JCache
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class JCacheFiarGameStorage
    implements FiarGameStorage
{
    private final Cache gameCache;

    /**
     * @throws CacheException If cache can't be created
     */
    public JCacheFiarGameStorage()
        throws CacheException
    {
        gameCache = CacheManager.getInstance().getCacheFactory().createCache( Collections.emptyMap() );
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
        gameCache.put( game.getGameId(), game );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateGame( FiarGame game )
    {
    }
}
