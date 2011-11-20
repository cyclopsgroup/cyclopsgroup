package org.cyclopsgroup.eulerer.p213;

import java.util.concurrent.atomic.AtomicReference;

import org.cyclopsgroup.eulerer.math.Matrix;
import org.cyclopsgroup.eulerer.math.MatrixInspector;

/**
 * Solution of problem 213
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
        System.out.println( "This is not working out yet" );
    }

    @SuppressWarnings( "unused" )
    private void doRun()
    {
        Calculator c = new Calculator( 30, 30 );
        Matrix<Double> result = c.calculate();
        final AtomicReference<Double> d = new AtomicReference<Double>( 0.0 );
        result.transform( new MatrixInspector<Double>()
        {
            @Override
            public Double inspect( int x, int y, Double value )
            {
                d.set( d.get() + value );
                return null;
            }
        } );
        System.out.println( 900.0 - d.get() );
    }
}
