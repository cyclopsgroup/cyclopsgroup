package org.cyclopsgroup.eulerer.p205;

import java.math.BigInteger;

public class ChanceCalculator
{
    private final Chance[] chances;

    private final int dices;

    private final int faces;

    public ChanceCalculator( int faces, int dices )
    {
        this.faces = faces;
        this.dices = dices;
        this.chances = new Chance[faces * dices];
        for ( int i = 0; i < chances.length; i++ )
        {
            chances[i] = new Chance();
        }
        buildChance( 0, 0 );

    }

    void calculate()
    {
        int rest = BigInteger.valueOf( faces ).pow( dices ).intValue();
        int less = 0;
        for ( Chance c : chances )
        {
            rest -= c.getValue();
            less += c.getValue();
            c.setLessAndMore( less, rest );
        }
    }

    private void buildChance( int start, int dice )
    {
        if ( dice == dices )
        {
            chances[start - 1].increase();
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
