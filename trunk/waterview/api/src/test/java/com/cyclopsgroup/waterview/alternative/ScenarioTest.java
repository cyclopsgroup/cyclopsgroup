package com.cyclopsgroup.waterview.alternative;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.ExecutionException;
import com.cyclopsgroup.waterview.spi.RequestResolver;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.RunDataSpi.Request;

public class ScenarioTest
    extends TestCase
{
    public void testSimpleScenario()
        throws Exception
    {
        StringWriter output = new StringWriter();

        ResolveRequestsValve resolverValve = new ResolveRequestsValve();
        RequestResolver resolver = new AbstractExtensionBasedRequestResolver( "tm" )
        {
            public void resolveRequest( RunDataSpi data, Request request )
                throws ExecutionException, IOException
            {
                data.getOutput().print( "!" );
                data.getOutput().print( request.getRequestPath() );
                data.getOutput().print( "!" );
            }
        };
        Pipeline pipeline = new Pipeline();
        pipeline.addValve( resolverValve );
        resolverValve.addRequestResolver( resolver );

        SinglePipelineWaterview w = new SinglePipelineWaterview( pipeline );
        w.registerPackage( "test", "com.cyclopsgroup.waterview.testui" );

        MockRunData data = new MockRunData( w, output );
        data.addRequest( "test", "/p/Test.tm" );

        w.processRunData( data );
        String result = output.toString();
        assertEquals( "!/p/Test.tm!", result );
    }
}