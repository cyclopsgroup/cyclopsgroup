package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;

@XmlRootElement( name = "Move" )
public class Move
{
    private DateTime moveTime;

    private String playerId;

    private PlayerRole playerRole;

    private int previousVersion;

    private int version;

    private int x;

    private int y;

    @XmlJavaTypeAdapter( DateTimeAdapter.class )
    @XmlElement
    public DateTime getMoveTime()
    {
        return moveTime;
    }

    @XmlElement
    public String getPlayerId()
    {
        return playerId;
    }

    @XmlElement
    public PlayerRole getPlayerRole()
    {
        return playerRole;
    }

    @XmlElement
    public final int getPreviousVersion()
    {
        return previousVersion;
    }

    @XmlElement
    public final int getVersion()
    {
        return version;
    }

    @XmlElement
    public final int getX()
    {
        return x;
    }

    @XmlElement
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
