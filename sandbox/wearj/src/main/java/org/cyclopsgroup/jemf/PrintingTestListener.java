package org.cyclopsgroup.jemf;

import java.lang.reflect.Method;

public class PrintingTestListener
    extends NoOpTestListener
{
    @Override
    public void methodLoopEnds( long result, Object lastReturnedValue, Method testMethod, Object testObject )
    {
        System.out.println( "Method " + testMethod.getName() + " ends with " + result + " nano seconds, returning " +
            lastReturnedValue );
    }
}
