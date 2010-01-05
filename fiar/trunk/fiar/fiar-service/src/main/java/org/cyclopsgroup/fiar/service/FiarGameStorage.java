package org.cyclopsgroup.fiar.service;

import org.cyclopsgroup.fiar.service.pojo.FiarGame;

public interface FiarGameStorage
{
    FiarGame loadGame( String gameId );

    void storeGame( FiarGame game );

    void updateGame( FiarGame game );
}
