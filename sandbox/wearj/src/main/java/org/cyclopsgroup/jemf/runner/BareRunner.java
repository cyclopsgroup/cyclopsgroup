package org.cyclopsgroup.jemf.runner;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.cyclopsgroup.jemf.NoOpTestListener;
import org.cyclopsgroup.jemf.TestListener;
import org.cyclopsgroup.jemf.TestMethod;
import org.cyclopsgroup.jemf.TestObject;

public class BareRunner
{

    public final TestListener getListener()
    {
        return listener;
    }

    public final void setListener( TestListener listener )
    {
        if ( listener == null )
        {
            throw new NullPointerException( "Listener can't be NULL" );
        }
        this.listener = listener;
    }

    private static final Object[] EMPTY_OBJECTS = new Object[] {};

    private TestListener listener = new NoOpTestListener();

    public void execute( TestObject test, int number )
    {
        List<Method> testMethods = new ArrayList<Method>();

        for ( Method method : test.getClass().getMethods() )
        {
            if ( method.isAnnotationPresent( TestMethod.class ) )
            {
                if ( method.getParameterTypes().length != 0 )
                {
                    throw new IllegalStateException( "Parameter list of method " + method + " has to be empty" );
                }
                if ( ( method.getModifiers() | Modifier.PUBLIC ) == 0 )
                {
                    throw new IllegalStateException( "Method " + method + " is not public" );
                }
                testMethods.add( method );
            }
        }
        Timer timer = new NanoSecondTimer();
        test.beforeTest();
        try
        {
            listener.objectStarts( test );
            for ( Method method : testMethods )
            {
                listener.methodLoopStarts( method, test );
                test.beforeMethod();
                try
                {
                    method.invoke( test, EMPTY_OBJECTS );
                }
                catch ( Throwable e )
                {
                    e.printStackTrace();
                }
                finally
                {
                    test.afterMethod();
                }
                long sum = 0;
                Object returnedValue = null;
                for ( int i = 0; i < number; i++ )
                {
                    test.beforeMethod();
                    try
                    {
                        listener.methodStarts( i, method, test );
                        
                        timer.begin();
                        try
                        {
                            returnedValue = method.invoke( test, EMPTY_OBJECTS );
                            long r = timer.end();
                            sum += r;
                            listener.methodEnds( r, returnedValue, i, method, test );
                        }
                        catch ( Throwable e )
                        {
                            e.printStackTrace();
                        }
                    }
                    finally
                    {
                        test.afterMethod();
                    }
                }
                long result = sum / number;
                listener.methodLoopEnds( result, returnedValue, method, test );
            }
            listener.objectEnds( test );
        }
        finally
        {
            test.afterTest();
        }
    }
}
