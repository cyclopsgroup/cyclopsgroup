package org.cyclopsgroup.eulerer.p220;

/**
 * Solution for problem 220
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Solution
    implements Runnable
{
    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        HeighwayDragon d = new HeighwayDragon( 50 );
        d.traverseFor( 1000000000000L );
        System.out.println( d.getTraveler().getCoordinate() );
    }
}
