package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement( name = "Move Result" )
public class Move
{
    private DateTime moveTime;

    private String playerId;

    private PlayerRole playerRole;

    private int previousVersion;

    private int version;

    private int x;

    private int y;

    public DateTime getMoveTime()
    {
        return moveTime;
    }

    public String getPlayerId()
    {
        return playerId;
    }

    public PlayerRole getPlayerRole()
    {
        return playerRole;
    }

    public final int getPreviousVersion()
    {
        return previousVersion;
    }

    public final int getVersion()
    {
        return version;
    }

    public final int getX()
    {
        return x;
    }

    public final int getY()
    {
        return y;
    }

    public void setMoveTime( DateTime moveTime )
    {
        this.moveTime = moveTime;
    }

    public void setPlayerId( String playerId )
    {
        this.playerId = playerId;
    }

    public void setPlayerRole( PlayerRole playerColor )
    {
        this.playerRole = playerColor;
    }

    public final void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
    }

    public final void setVersion( int version )
    {
        this.version = version;
    }

    public final void setX( int x )
    {
        this.x = x;
    }

    public final void setY( int y )
    {
        this.y = y;
    }

}
