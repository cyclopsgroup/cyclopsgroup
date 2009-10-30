package org.cyclopsgroup.eulerer.p205;

/**
 * Solution of problem 205
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Solution
{
    /**
     * @param args Dull command line arguments
     */
    public static void main( String[] args )
    {
        ChanceCalculator peter = new ChanceCalculator( 4, 9 );
        peter.calculate();
        ChanceCalculator colin = new ChanceCalculator( 6, 6 );

        // Consider colin
        long win = 0, lose = 0, draw = 0;
        for ( int i = 1; i <= 36; i++ )
        {
            int c = colin.chanceOf( i ).value();
            Chance p = peter.chanceOf( i );

            win += p.less() * c;
            draw += p.value() * c;
            lose += p.more() * c;
        }
        System.out.println( "win=" + win + " lose=" + lose + " draw=" + draw );
    }
}
