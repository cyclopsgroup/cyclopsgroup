package org.cyclopsgroup.jemf;

import java.lang.reflect.Method;

public class NoOpTestListener
    implements TestListener
{

    @Override
    public void methodEnds( long result, Object returnedValue, int index, Method testMethod, Object testObject )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void methodLoopEnds( long result, Object lastReturnedValue, Method testMethod, Object testObject )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void methodLoopStarts( Method testMethod, Object testObject )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void methodStarts( int index, Method testMethod, Object testObject )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void objectEnds( Object testObject )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void objectStarts( Object testObject )
    {
        // TODO Auto-generated method stub

    }

}
