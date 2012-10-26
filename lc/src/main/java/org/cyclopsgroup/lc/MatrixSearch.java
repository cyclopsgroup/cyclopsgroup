package org.cyclopsgroup.lc;

import java.util.Arrays;

public class MatrixSearch
{
    private static final int COLUMNS = 60;

    private static final int ROWS = 10;

    public static void main( String[] args )
    {
        int[][] matrix = new int[ROWS][COLUMNS];
        for ( int i = 0; i < ROWS; i++ )
        {
            for ( int j = 0; j < COLUMNS; j++ )
            {
                matrix[i][j] = ( i * COLUMNS + j );
            }
        }

        System.out.println( new MatrixSearch( COLUMNS, ROWS ).exists( matrix, 160 ) );
    }

    private final int columns;

    private final int rows;

    public MatrixSearch( int columns, int rows )
    {
        this.columns = columns;
        this.rows = rows;
    }

    /**
     * Return an integer array with values from a column in matrix
     *
     * @param index Zero based x position of the column
     */
    private int[] columnOf( int[][] matrix, int index )
    {
        int[] result = new int[rows];
        for ( int i = 0; i < rows; i++ )
        {
            result[i] = matrix[i][index];
        }
        return result;
    }

    public boolean exists( int[][] matrix, int num )
    {
        return exists( matrix, num, 0, columns, 0, rows, true );
    }

    private boolean exists( int[][] matrix, int num, int xInclusiveStart, int xExclusiveEnd, int yInclusiveStart,
                            int yExclusiveEnd, boolean contractHorizontally )
    {
        System.out.println( "Search x=[" + xInclusiveStart + "-" + xExclusiveEnd + "], y=[" + yInclusiveStart + "-"
            + yExclusiveEnd + "] and contract " + ( contractHorizontally ? "horizontally" : "vertically" ) );

        // Check if there's one column or one row, this part is the same as original version
        // If there's only one column left
        if ( xExclusiveEnd - xInclusiveStart <= 1 )
        {
            return Arrays.binarySearch( columnOf( matrix, xInclusiveStart ), yInclusiveStart, yExclusiveEnd, num ) >= 0;
        }

        // If there's only one row left
        if ( yExclusiveEnd - yInclusiveStart <= 1 )
        {
            return Arrays.binarySearch( matrix[yInclusiveStart], xInclusiveStart, xExclusiveEnd, num ) >= 0;
        }

        if ( contractHorizontally )
        {
            // If a number on top row is greater than num, all numbers on its right are greater than num
            // However if not, it doesn't mean on numbers on its left are smaller than num. This is the mistake
            // the original code made.
            int[] top = matrix[yInclusiveStart];
            int position = Arrays.binarySearch( top, xInclusiveStart, xExclusiveEnd, num );
            if ( position >= 0 )
            {
                return true;
            }
            int ceiling = -( position + 1 ); // Position of the least number greater than num on top row

            // If a number on bottom row is smaller than num, all numbers on its left are smaller than num
            int[] bottom = matrix[yExclusiveEnd - 1];
            position = Arrays.binarySearch( bottom, xInclusiveStart, xExclusiveEnd, num );
            if ( position >= 0 )
            {
                return true;
            }
            int floor = -( position + 2 ); // Position of the greatest number less than num on bottom row

            assert ceiling > floor;
            return exists( matrix, num, floor + 1, ceiling, yInclusiveStart, yExclusiveEnd, false );
        }
        else
        {
            // If a number on left column is greater than num, all numbers below it are greater than num
            int[] left = columnOf( matrix, xInclusiveStart );
            int position = Arrays.binarySearch( left, yInclusiveStart, yExclusiveEnd, num );
            if ( position >= 0 )
            {
                return true;
            }
            int ceiling = -( position + 1 ); // Position of the least number greater than num on left column

            // If a number on right column is smaller than num, all numbers above it are smaller than num
            int[] right = columnOf( matrix, xExclusiveEnd - 1 );
            position = Arrays.binarySearch( right, yInclusiveStart, yExclusiveEnd, num );
            if ( position >= 0 )
            {
                return true;
            }
            int floor = -( position + 2 ); // Position of the greatest number less than num on right column

            assert ceiling > floor;
            return exists( matrix, num, xInclusiveStart, xExclusiveEnd, floor + 1, ceiling, true );
        }
    }
}
