package com.cyclopsgroup.waterview.impl.valves;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.DefaultContext;
import com.cyclopsgroup.waterview.impl.RenderPageValve;
import com.cyclopsgroup.waterview.spi.DefaultResourceRegistry;
import com.cyclopsgroup.waterview.spi.TemplateEngine;

public class RenderPageValveTest
    extends TestCase
{
    private class TE
        implements TemplateEngine
    {
        public void mergeTemplate( String templatePath, Context context, Writer output )
            throws Exception
        {
            output.write( "?" + templatePath + "?" );
        }
    }

    private RenderPageValve rpv;;

    @Override
    protected void setUp()
    {
        DefaultResourceRegistry drr = new DefaultResourceRegistry();
        drr.addPackage( "a", "a.a.a" );
        rpv = new RenderPageValve( "/a/DefaultLayout.vm", drr );
        rpv.addTemplateEngine( ".+\\.vm", new TE() );
    }

    public void testRenderPage()
        throws Exception
    {
        StringWriter output = new StringWriter();
        rpv.renderPage( null, "a/X.vm", new DefaultContext( new HashMap<String, Object>() ), output );
        output.flush();
        assertEquals( "?a/X.vm?", output.toString() );
    }
}
