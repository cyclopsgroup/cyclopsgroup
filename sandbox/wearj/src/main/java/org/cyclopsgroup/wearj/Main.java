package org.cyclopsgroup.wearj;

import org.cyclopsgroup.jemf.PrintingTestListener;
import org.cyclopsgroup.jemf.runner.BareRunner;

public class Main
{
    public static void main( String args[] )
    {
        BareRunner runner = new BareRunner();
        runner.setListener( new PrintingTestListener() );
        // runner.execute( new StringConcat( "Jerry" ), 1 );

        runner.execute( new DollarToCents( "1.2", 1.2 ), 1000 );

        for ( int i = 0; i < 2; i++ )
        {
            Object x = i % 2 == 0 ? 2 : "x";
            System.out.println( "x is a " + x.getClass() );
        }
    }
}
