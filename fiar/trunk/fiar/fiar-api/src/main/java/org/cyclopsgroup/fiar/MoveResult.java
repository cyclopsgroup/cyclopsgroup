package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Move Result" )
public class MoveResult
{
    private int previousVersion;

    private int version;

    private int x;

    private int y;

    private String playerId;

    private PlayerColor playerColor;

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
