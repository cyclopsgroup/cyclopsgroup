package com.cyclopsgroup.waterview;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

public class DefaultContextTest
{
    private void testType( Class<?> c )
    {

    }

    @Test
    public void cast()
    {
        testType( String.class );
    }

    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( DefaultContextTest.class );
    }
}
