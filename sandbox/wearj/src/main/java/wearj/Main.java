package wearj;

import org.cyclopsgroup.jemf.PrintingTestListener;
import org.cyclopsgroup.jemf.runner.BareRunner;

public class Main
{
    public static void main( String args[] )
    {
        BareRunner runner = new BareRunner();
        runner.setListener( new PrintingTestListener() );
        // runner.execute( new StringConcat( 22 ), 1 );

        // runner.execute( new DollarToCents( "1.2", 1.2 ), 1000 );

        runner.execute( new ConstructorPerformance(), 10000 );
    }
}
