package org.cyclopsgroup.fiar.service;

import org.cyclopsgroup.fiar.service.pojo.FiarGame;

public interface GameStorageService
{
    boolean deleteGame( String gameId );

    FiarGame loadGame( String gameId );

    void storeGame( FiarGame game );

    void updateGame( FiarGame game );
}
