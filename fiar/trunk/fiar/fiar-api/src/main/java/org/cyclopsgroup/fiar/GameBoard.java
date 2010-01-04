package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "GameBoard" )
public class GameBoard
{
    private int height;

    private int width;

    @XmlElement
    public final int getHeight()
    {
        return height;
    }

    @XmlElement
    public final int getWidth()
    {
        return width;
    }

    public final void setHeight( int height )
    {
        this.height = height;
    }

    public final void setWidth( int width )
    {
        this.width = width;
    }
}
