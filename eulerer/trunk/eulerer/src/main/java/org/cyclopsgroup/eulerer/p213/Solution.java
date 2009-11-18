package org.cyclopsgroup.eulerer.p213;

import java.util.Arrays;

import org.cyclopsgroup.eulerer.math.RationalNumber;

/**
 * Solution of problem 213
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Solution
    implements Runnable
{
    private static final RationalNumber THREE = new RationalNumber( 3 );

    private final RationalNumber[][] options;

    public Solution()
    {
        options = newGrid( new RationalNumber( 4 ) );
        for ( int i = 1; i < 29; i++ )
        {
            options[0][i] = options[29][i] = options[i][0] = options[i][29] = THREE;
        }
        options[0][0] = options[0][29] = options[29][0] = options[29][29] = new RationalNumber( 2 );
    }

    public static void main( String[] args )
    {
        new Solution().run();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        RationalNumber[][] grid = newGrid( RationalNumber.ONE );
        RationalNumber[][] next;
        for ( int i = 0; i < 49; i++ )
        {
            next = newGrid( null );
            // Four corners
            RationalNumber corner = grid[0][1].add( grid[1][0] ).divide( THREE );
            next[0][0] = next[0][29] = next[29][0] = next[29][29] = corner;

            // For borders
            for ( int j = 1; j < 15; j++ )
            {
                RationalNumber border =
                    grid[0][j - 1].divide( options[0][j - 1] ).add( grid[0][j + 1].divide( options[0][j + 1] ) ).add(
                                                                                                                      grid[1][j].divide( options[1][j] ) );
                next[0][j] =
                    next[0][29 - j] =
                        next[29][j] =
                            next[29][29 - j] = next[j][0] = next[29 - j][0] = next[j][29] = next[29 - j][29] = border;
            }

            for ( int x = 1; x < 15; x++ )
            {
                for ( int y = 1; y <= x; y++ )
                {
                    RationalNumber spot = grid[x - 1][y].divide( options[x - 1][y] );
                    spot = spot.add( grid[x + 1][y].divide( options[x + 1][y] ) );
                    spot = spot.add( grid[x][y - 1].divide( options[x][y - 1] ) );
                    spot = spot.add( grid[x][y + 1].divide( options[x][y + 1] ) );
                    next[x][y] =
                        next[x][29 - y] =
                            next[29 - x][y] =
                                next[29 - x][29 - y] =
                                    next[y][x] = next[29 - y][x] = next[y][29 - x] = next[29 - y][29 - x] = spot;
                }
            }
            grid = next;
        }

        double probability = Math.pow( 2.0 / 3.0, grid[0][1].toDouble() * 2 );
        for ( int i = 1; i < 15; i++ )
        {
            probability +=
                skipProbability( 0, i - 1, grid ) * skipProbability( 0, i + 1, grid ) * skipProbability( 1, i, grid )
                    * 2;
        }

        for ( int x = 1; x < 15; x++ )
        {
            for ( int y = 1; y <= x; y++ )
            {
                double prob = skipProbability( x - 1, y, grid );
                prob *= skipProbability( x + 1, y, grid );
                prob *= skipProbability( x, y - 1, grid );
                prob *= skipProbability( x, y + 1, grid );
                probability += prob * 2;
            }
        }

        System.out.println( probability * 4 );
    }

    private double skipProbability( int x, int y, RationalNumber[][] grid )
    {
        return Math.pow( ( options[x][y].toDouble() - 1 ) / options[x][y].toDouble(), grid[x][y].toDouble() );
    }

    private static RationalNumber[][] newGrid( RationalNumber initialValue )
    {
        RationalNumber[][] grid = new RationalNumber[30][30];
        if ( initialValue != null )
        {
            for ( int i = 0; i < 30; i++ )
            {
                RationalNumber[] row = grid[i];
                Arrays.fill( row, initialValue );
            }
        }
        return grid;
    }
}
