package org.cyclopsgroup.jemf;

import java.lang.reflect.Method;

public interface TestListener
{
    void objectStarts( Object testObject );

    void methodLoopStarts( Method testMethod, Object testObject );

    void methodStarts( int index, Method testMethod, Object testObject );

    void methodLoopEnds( long result, Object lastReturnedValue, Method testMethod, Object testObject );

    void methodEnds( long result, Object returnedValue, int index, Method testMethod, Object testObject );

    void objectEnds( Object testObject );
}
