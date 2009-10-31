package org.cyclopsgroup.eulerer.p220;

public class Solution
    implements Runnable
{
    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        HeighwayDragon d = new HeighwayDragon( 10 );
        d.traverseFor( 500L );
        System.out.println( d.position() );
    }
}
