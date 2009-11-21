package org.cyclopsgroup.eulerer.math;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.eulerer.math.Matrix;
import org.cyclopsgroup.eulerer.math.MatrixInspector;
import org.junit.Test;

/**
 * Test of {@link Matrix} class
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class MatrixTest
{
    /**
     * Verify traverse function
     */
    @Test
    public void testTraverse()
    {
        Matrix<String> m = new Matrix<String>( 3, 2, "0" );
        final StringBuilder p = new StringBuilder();
        final StringBuilder b = new StringBuilder();
        m.traverse( new MatrixInspector<String>()
        {
            @Override
            public String inspect( int x, int y, String value )
            {
                b.append( value );
                p.append( x + "" + y );
                return null;
            }
        } );
        assertEquals( "000000", b.toString() );
        assertEquals( "001020011121", p.toString() );
    }
}
