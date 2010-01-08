package org.cyclopsgroup.fiar.service.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class FiarGame
    implements Serializable
{
    private static final long serialVersionUID = 1196069906317471250L;

    private DateTime creationDate;

    private String creatorId;

    private String defensePlayerId;

    private String gameId;

    private String gameName;

    private FiarGameState gameState = FiarGameState.PENDING;

    private int height;

    private List<FiarGameMove> moves = new ArrayList<FiarGameMove>();

    private FiarGamePlayer nextMovePlayer = FiarGamePlayer.OFFENSE;

    private String offensePlayerId;

    private int previousVersion;

    private int version;

    private int width;

    public final DateTime getCreationDate()
    {
        return creationDate;
    }

    public final String getCreatorId()
    {
        return creatorId;
    }

    public final String getDefensePlayerId()
    {
        return defensePlayerId;
    }

    public final String getGameId()
    {
        return gameId;
    }

    public final String getGameName()
    {
        return gameName;
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

    public final FiarGamePlayer getNextMovePlayer()
    {
        return nextMovePlayer;
    }

    public final String getOffensePlayerId()
    {
        return offensePlayerId;
    }

    public final int getPreviousVersion()
    {
        return previousVersion;
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

    public final void setDefensePlayerId( String defensePlayerId )
    {
        this.defensePlayerId = defensePlayerId;
    }

    public final void setGameId( String gameId )
    {
        this.gameId = gameId;
    }

    public final void setGameName( String gameName )
    {
        this.gameName = gameName;
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

    public final void setNextMovePlayer( FiarGamePlayer nextMovePlayer )
    {
        this.nextMovePlayer = nextMovePlayer;
    }

    public final void setOffensePlayerId( String offensePlayerId )
    {
        this.offensePlayerId = offensePlayerId;
    }

    public final void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
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
