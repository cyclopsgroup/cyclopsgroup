package org.cyclopsgroup.fiar.service.pojo;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * A move in game
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class FiarGameMove
    implements Serializable
{
    private static final long serialVersionUID = -4759818334918145209L;

    private DateTime moveDate;

    private FiarGamePlayer player;

    private int previousVersion;

    private String userId;

    private int version;

    private int x;

    private int y;

    public final DateTime getMoveDate()
    {
        return moveDate;
    }

    public final FiarGamePlayer getPlayer()
    {
        return player;
    }

    public final int getPreviousVersion()
    {
        return previousVersion;
    }

    public final String getUserId()
    {
        return userId;
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

    public final void setMoveDate( DateTime moveDate )
    {
        this.moveDate = moveDate;
    }

    public final void setPlayer( FiarGamePlayer player )
    {
        this.player = player;
    }

    public final void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
    }

    public final void setUserId( String userId )
    {
        this.userId = userId;
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
