package org.cyclopsgroup.fiar.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class FiarGame
    implements Serializable
{
    private DateTime creationDate;

    private String creatorId;

    private String gameId;

    private FiarGameState gameState;

    private int height;

    private List<FiarGameMove> moves = new ArrayList<FiarGameMove>();

    private int version;

    private int width;

    public FiarGame()
    {
    }

    public final DateTime getCreationDate()
    {
        return creationDate;
    }

    public final String getCreatorId()
    {
        return creatorId;
    }

    public final String getGameId()
    {
        return gameId;
    }

    public final FiarGameState getGameState()
    {
        return gameState;
    }

    public final int getHeight()
    {
        return height;
    }

    public final List<FiarGameMove> getMoves()
    {
        return moves;
    }

    public final int getVersion()
    {
        return version;
    }

    public final int getWidth()
    {
        return width;
    }

    public final void setCreationDate( DateTime creationDate )
    {
        this.creationDate = creationDate;
    }

    public final void setCreatorId( String creatorId )
    {
        this.creatorId = creatorId;
    }

    public final void setGameId( String gameId )
    {
        this.gameId = gameId;
    }

    public final void setGameState( FiarGameState gameState )
    {
        this.gameState = gameState;
    }

    public final void setHeight( int height )
    {
        this.height = height;
    }

    public final void setMoves( List<FiarGameMove> moves )
    {
        this.moves = moves;
    }

    public final void setVersion( int version )
    {
        this.version = version;
    }

    public final void setWidth( int width )
    {
        this.width = width;
    }
}
