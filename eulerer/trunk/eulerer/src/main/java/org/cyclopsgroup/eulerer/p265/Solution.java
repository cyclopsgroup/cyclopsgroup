package org.cyclopsgroup.eulerer.p265;

/**
 * Solution of <a href="http://projecteuler.net/index.php?section=problems&id=265>problem 265</a>
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
        Problem p = new Problem( 5 );
        long x = 0;
        for ( long i : p.solve() )
        {
            x += i;
        }
        System.out.println( x );
    }
}
