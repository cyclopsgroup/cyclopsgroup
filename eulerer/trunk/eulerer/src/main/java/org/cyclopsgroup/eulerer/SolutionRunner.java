package org.cyclopsgroup.eulerer;

import java.math.BigDecimal;

/**
 * Main class that runs all solutions and prints summary
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SolutionRunner
{
    private void execute()
    {
        for ( int i = 1; i < 1000; i++ )
        {
            Class<?> solutionType;
            try
            {
                solutionType = Class.forName( String.format( "org.cyclopsgroup.eulerer.p%03d.Solution", i ) );
            }
            catch ( ClassNotFoundException e )
            {
                continue;
            }
            Runnable solution;
            try
            {
                solution = (Runnable) solutionType.newInstance();
            }
            catch ( InstantiationException e )
            {
                throw new RuntimeException( "Solution " + solutionType + "can't be instantiated", e );
            }
            catch ( IllegalAccessException e )
            {
                throw new RuntimeException( "Solution " + solutionType + "can't be instantiated", e );
            }
            System.out.println( ">>>>>> Runnining solution of problem " + i );
            long start = System.nanoTime();
            solution.run();
            long elapsed = System.nanoTime() - start;
            System.out.println();
            System.out.println( "<<<<<< Solution " + i + " took " + BigDecimal.valueOf( elapsed ).movePointLeft( 6 )
                + " ms" );
        }
    }

    /**
     * @param args Command line args
     */
    public static void main( String[] args )
    {
        new SolutionRunner().execute();
    }
}
