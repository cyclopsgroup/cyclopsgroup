package org.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.PackageListModuleResolver;
import org.cyclopsgroup.waterview.impl.pipeline.Pipeline;
import org.cyclopsgroup.waterview.impl.render.ExtensionBasedRenderer;
import org.cyclopsgroup.waterview.impl.render.VelocityRenderer;
import org.cyclopsgroup.waterview.impl.valve.ParseRequestPathValve;
import org.cyclopsgroup.waterview.impl.valve.RenderPageValve;
import org.cyclopsgroup.waterview.ipa.Renderer;
import org.cyclopsgroup.waterview.ipa.Valve;

public class DefaultWebProcessor
{
    private final Pipeline pipeline;

    public DefaultWebProcessor()
    {
        ModuleResolver moduleResolver =
            new PackageListModuleResolver( Arrays.asList( "org.cyclopsgroup.waterview.impl.web" ) );
        Map<String, Renderer> rendererMap = new HashMap<String, Renderer>();
        rendererMap.put( "vm", new VelocityRenderer() );
        Renderer renderer = new ExtensionBasedRenderer( rendererMap );

        List<Valve> valves = new ArrayList<Valve>();
        valves.add( new ParseRequestPathValve() );
        valves.add( new RenderPageValve( moduleResolver, renderer ) );
        pipeline = new Pipeline( valves );
    }

    public void process( WebContext context )
        throws IOException
    {
        pipeline.invoke( context );
    }
}
