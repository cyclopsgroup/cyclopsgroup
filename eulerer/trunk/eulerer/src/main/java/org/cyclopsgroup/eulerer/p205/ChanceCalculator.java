package org.cyclopsgroup.eulerer.p205;

import java.math.BigInteger;

public class ChanceCalculator
{
    private final Chance[] chances;

    private final int dices;

    private final int faces;

    private final long total;

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
            chances[i] = new Chance( total );
        }
        buildChance( 0, 0 );
    }

    /**
     * Adjust less and more of each chance
     */
    void calculate()
    {
        int rest = BigInteger.valueOf( faces ).pow( dices ).intValue();
        int less = 0;
        for ( Chance c : chances )
        {
            rest -= c.value();
            less += c.value();
            c.adjust( less, rest );
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

    Chance chanceOf( int value )
    {
        return chances[value - 1];
    }

    public void print()
    {
        int line = 0;
        for ( Chance chance : chances )
        {
            System.out.println( ++line + "=" + chance );
        }
    }
}
