package org.cyclopsgroup.waterview.impl.assembly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.WebContextProcessor;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.PackageListModuleResolver;
import org.cyclopsgroup.waterview.impl.pipeline.Pipeline;
import org.cyclopsgroup.waterview.impl.render.ExtensionBasedRenderer;
import org.cyclopsgroup.waterview.impl.valve.ParseRequestPathValve;
import org.cyclopsgroup.waterview.impl.valve.RenderPageValve;
import org.cyclopsgroup.waterview.impl.velocity.VelocityEngineBuilder;
import org.cyclopsgroup.waterview.impl.velocity.VelocityRenderer;
import org.cyclopsgroup.waterview.ipa.Renderer;
import org.cyclopsgroup.waterview.ipa.Valve;

public class DefaultWebContextProcessor
    implements WebContextProcessor
{
    private final Pipeline pipeline;

    public DefaultWebContextProcessor( String templateRoot )
    {
        List<String> packageNames = Arrays.asList( "org.cyclopsgroup.waterview.impl.web" );
        ModuleResolver moduleResolver = new PackageListModuleResolver( packageNames );
        Map<String, Renderer> rendererMap = new HashMap<String, Renderer>();
        VelocityEngineBuilder veb = new VelocityEngineBuilder();
        veb.addFileSystemResourceLoader( templateRoot );
        veb.addDeepClassPathResourceLoaders( packageNames );
        rendererMap.put( "vm", new VelocityRenderer( veb.newEngine() ) );
        Renderer renderer = new ExtensionBasedRenderer( rendererMap );
        List<Valve> valves = new ArrayList<Valve>();
        valves.add( new ParseRequestPathValve( "/index.vm" ) );
        valves.add( new RenderPageValve( moduleResolver, renderer, "_layout_/default_layout.vm" ) );
        pipeline = new Pipeline( valves );
    }

    public void process( WebContext context )
        throws IOException
    {
        pipeline.invoke( context );
    }
}
