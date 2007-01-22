package com.cyclopsgroup.waterview.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

public class PipelineTest
    extends TestCase
{
    private class MockValve
        implements Valve
    {
        private List<String> values;

        private String id;

        private MockValve( List<String> values, String id )
        {
            this.values = values;
            this.id = id;
        }

        public void invoke( RunDataSpi data, PipelineContext context )
            throws Exception
        {
            values.add( id );
            context.invokeNextValve( data );
        }
    }

    public void testRun()
        throws Exception
    {
        List<String> values = new ArrayList<String>();
        Pipeline p = new Pipeline();
        p.addValve( new MockValve( values, "aaa" ) );
        p.addValve( new MockValve( values, "bbb" ) );

        p.run( null );

        assertEquals( 2, values.size() );
        assertEquals( "aaa", values.get( 0 ) );
        assertEquals( "bbb", values.get( 1 ) );
    }
}
