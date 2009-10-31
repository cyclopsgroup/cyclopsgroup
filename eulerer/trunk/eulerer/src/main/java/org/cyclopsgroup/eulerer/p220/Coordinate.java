package org.cyclopsgroup.eulerer.p220;

/**
 * Cooridnate are two mutable integers, x and y
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Coordinate
{
    int x, y;

    /**
     * @return Horizontal position
     */
    public final int getX()
    {
        return x;
    }

    /**
     * @return Vertical position
     */
    public final int getY()
    {
        return y;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return x + ":" + y;
    }
}
