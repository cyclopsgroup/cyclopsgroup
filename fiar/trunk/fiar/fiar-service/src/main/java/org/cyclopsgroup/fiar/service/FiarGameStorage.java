package org.cyclopsgroup.fiar.service;

public interface FiarGameStorage
{
    void storeGame( FiarGame game );

    FiarGame loadGame( String gameId );
}
