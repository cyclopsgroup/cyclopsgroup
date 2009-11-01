package org.cyclopsgroup.eulerer.p205;

/**
 * Solution of problem 205
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
class DiceGame
{
    /**
     * Solve the problem by compare Peter with Colin
     */
    void comparePeterAndColin()
    {
        ChanceCalculator peter = new ChanceCalculator( 4, 9 );
        ChanceCalculator colin = new ChanceCalculator( 6, 6 );
        colin.adjustPositions();

        // Consider peter
        long win = 0, lose = 0, draw = 0;
        for ( int i = 1; i <= 36; i++ )
        {
            int c = peter.chanceOf( i ).value();
            Chance p = colin.chanceOf( i );

            win += p.less() * c;
            draw += p.value() * c;
            lose += p.greater() * c;
        }
        System.out.printf( "Peter: win=%d, lose=%d, draw=%d, chance to win=%1.7fl", win, lose, draw,
                           ( win / (double) ( win + lose + draw ) ) );
        System.out.println();
    }
}
