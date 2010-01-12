package org.cyclopsgroup.fiar.service;

import java.util.ArrayList;
import java.util.List;

import org.cyclopsgroup.fiar.service.pojo.FiarGame;
import org.cyclopsgroup.fiar.service.pojo.FiarGameMove;
import org.cyclopsgroup.fiar.service.pojo.FiarGameState;

class GameManager
{
    void addMove( FiarGame game, FiarGameMove move )
    {
        synchronized ( game )
        {
            if ( game.getGameState() != FiarGameState.ONGOING )
            {
                throw new IllegalStateException( "Move can only be made when state is ONGING. Current state is "
                    + game.getGameState() );
            }
            if ( game.getPreviousVersion() == move.getPreviousVersion() )
            {
                // A duplicated call
                move.setVersion( game.getVersion() );
                return;
            }
            if ( !game.getNextMovePlayer().getUserIdOf( game ).equals( move.getUserId() ) )
            {
                throw new IllegalStateException( "It's not " + move.getUserId() + "'s turn to make move" );
            }
            int previousVersion = game.getVersion();
            move.setPreviousVersion( previousVersion );
            game.setPreviousVersion( previousVersion );
            int newVersion = previousVersion + 1;
            move.setVersion( newVersion );
            game.setVersion( newVersion );
            move.setPlayer( game.getNextMovePlayer() );
            game.setNextMovePlayer( game.getNextMovePlayer().getCounterPart() );
            game.getMoves().add( move );
        }
    }

    int joinGame( FiarGame game, String userId )
    {
        int version;
        synchronized ( game )
        {
            if ( game.getGameState() == FiarGameState.ONGOING && game.getDefensePlayerId().equals( userId ) )
            {
                return game.getVersion();
            }
            if ( game.getGameState() != FiarGameState.PENDING )
            {
                throw new IllegalStateException( "User can join only when state of game is PENDING. Current state is "
                    + game.getGameState() );
            }
            game.setDefensePlayerId( userId );
            game.setGameState( FiarGameState.ONGOING );
            version = game.getVersion() + 1;
            game.setVersion( version );
        }
        return version;
    }

    List<FiarGameMove> getMovesFrom( int startVersion, FiarGame game )
    {
        List<FiarGameMove> results = new ArrayList<FiarGameMove>();
        for ( FiarGameMove move : game.getMoves() )
        {
            if ( move.getPreviousVersion() >= startVersion )
            {
                results.add( move );
            }
        }
        return results;
    }
}
