package org.cyclopsgroup.eulerer.p213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.eulerer.math.Coordinate;
import org.cyclopsgroup.eulerer.math.Matrix;
import org.cyclopsgroup.eulerer.math.MatrixInspector;

/**
 * FIXME This is a very primitive and inefficient implementation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
class Calculator
{
    private final int width, height;

    private final Matrix<List<Coordinate>> neighbourIndex;

    Calculator( int width, int height )
    {
        Validate.isTrue( width > 0, "Invalid width " + width );
        Validate.isTrue( height > 0, "Invalid height " + height );
        this.width = width;
        this.height = height;

        // Build index
        this.neighbourIndex = new Matrix<List<Coordinate>>( width, height );
        neighbourIndex.traverse( new MatrixInspector<List<Coordinate>>()
        {
            @Override
            public List<Coordinate> inspect( int x, int y, List<Coordinate> value )
            {
                List<Coordinate> candidates =
                    Arrays.asList( new Coordinate( x - 1, y ), new Coordinate( x + 1, y ), new Coordinate( x, y - 1 ),
                                   new Coordinate( x, y + 1 ) );
                List<Coordinate> result = new ArrayList<Coordinate>( 4 );
                for ( Coordinate c : candidates )
                {
                    if ( c.x >= 0 && c.x < Calculator.this.width && c.y >= 0 && c.y < Calculator.this.height )
                    {
                        result.add( c );
                    }
                }
                neighbourIndex.set( x, y, result );
                return null;
            }
        } );
    }

    Matrix<Double> calculate()
    {
        final Matrix<Matrix<Double>> fleas = new Matrix<Matrix<Double>>( width, height );
        fleas.traverse( new MatrixInspector<Matrix<Double>>()
        {
            @Override
            public Matrix<Double> inspect( int x, int y, Matrix<Double> value )
            {
                if ( value != null )
                {
                    return null;
                }
                System.out.println( "Calculating flea " + x + ":" + y );
                Matrix<Double> v = calculateProbabilitiesForFlea( x, y );
                fleas.set( x, y, v );
                fleas.set( width - 1 - x, y, v );
                fleas.set( x, height - 1 - y, v );
                fleas.set( width - 1 - x, height - 1 - y, v );
                return null;
            }
        } );

        final Matrix<Double> vacancyProbabilities = new Matrix<Double>( width, height, 1.0 );
        fleas.traverse( new MatrixInspector<Matrix<Double>>()
        {
            @Override
            public Matrix<Double> inspect( int x, int y, Matrix<Double> value )
            {
                value.traverse( new MatrixInspector<Double>()
                {

                    @Override
                    public Double inspect( int x, int y, Double value )
                    {
                        double oldValue = vacancyProbabilities.get( x, y );
                        double newValue = oldValue * ( 1.0 - value );
                        vacancyProbabilities.set( x, y, newValue );
                        return null;
                    }
                } );
                return null;
            }
        } );
        return vacancyProbabilities;
    }

    private Matrix<Double> calculateProbabilitiesForFlea( int x, int y )
    {
        Matrix<Double> m = new Matrix<Double>( 30, 30, 0.0 );
        m.set( x, y, 1.0 );

        for ( int i = 0; i < 50; i++ )
        {
            final Matrix<Double> previous = m;
            m = previous.transform( new MatrixInspector<Double>()
            {
                @Override
                public Double inspect( int x, int y, Double value )
                {
                    double v = 0.0;
                    for ( Coordinate neighbour : neighbourIndex.get( x, y ) )
                    {
                        double neighbourValue = previous.get( neighbour.x, neighbour.y );
                        if ( neighbourValue == 0.0 )
                        {
                            continue;
                        }
                        int chances = neighbourIndex.get( neighbour.x, neighbour.y ).size();
                        v += neighbourValue / chances;
                    }
                    return v;
                }
            } );
        }
        return m;
    }
}
