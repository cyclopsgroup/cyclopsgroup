package org.cyclopsgroup.eulerer.p205;

import java.math.BigInteger;

/**
 * Based on number of faces and dices, calculate chance of of each possible value
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class ChanceCalculator
{
    private final Chance[] chances;

    private final int dices;

    private final int faces;

    private final long total;

    /**
     * @param faces Number of faces for each dice
     * @param dices Number of total dices
     */
    public ChanceCalculator( int faces, int dices )
    {
        if ( faces <= 0 || dices <= 0 )
        {
            throw new IllegalArgumentException( String.format( "Invalid input value %d, %d", faces, dices ) );
        }
        this.faces = faces;
        this.dices = dices;
        this.total = BigInteger.valueOf( faces ).pow( dices ).longValue();
        this.chances = new Chance[faces * dices];
        for ( int i = 0; i < chances.length; i++ )
        {
            chances[i] = new Chance();
        }
        buildChance( 0, 0 );
    }

    /**
     * Adjust position of each chance so that each chance knows chance of being less or greater
     */
    void adjustPositions()
    {
        long rest = total;
        long less = 0;
        for ( Chance c : chances )
        {
            rest -= c.value();
            c.position( less, rest );
            less += c.value();
        }
    }

    private void buildChance( int start, int dice )
    {
        if ( dice == dices )
        {
            chances[start - 1].increaseValue();
            return;
        }
        for ( int i = 1; i <= faces; i++ )
        {
            buildChance( start + i, dice + 1 );
        }
    }

    /**
     * Chance of being equal to given value
     *
     * @param value Given value to compare to
     * @return Chance for this value
     */
    Chance chanceOf( int value )
    {
        return chances[value - 1];
    }
}
