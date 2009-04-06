package org.cyclopsgroup.waterview.impl.assembly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.pipeline.Pipeline;
import org.cyclopsgroup.waterview.impl.render.ExtensionBasedRenderer;
import org.cyclopsgroup.waterview.impl.valve.ParseRequestPathValve;
import org.cyclopsgroup.waterview.impl.valve.RenderPageValve;
import org.cyclopsgroup.waterview.impl.velocity.VelocityEngineBuilder;
import org.cyclopsgroup.waterview.impl.velocity.VelocityRenderer;
import org.cyclopsgroup.waterview.spi.ComponentResolver;
import org.cyclopsgroup.waterview.spi.Renderer;
import org.cyclopsgroup.waterview.spi.Valve;

/**
 * A default hard-coded processor implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultWebContextProcessor
    implements WebContextProcessor
{
    private final Pipeline pipeline;

    /**
     * @param templateRoot Path of templates
     */
    public DefaultWebContextProcessor( String templateRoot, ComponentResolver resolver )
    {
        Validate.notNull( resolver, "Component resolver can't be NULL" );
        ModuleResolver moduleResolver = new ModuleResolver( resolver );
        Map<String, Renderer> rendererMap = new HashMap<String, Renderer>();
        VelocityEngineBuilder veb = new VelocityEngineBuilder();
        veb.addFileSystemResourceLoader( templateRoot );
        veb.addDeepClassPathResourceLoaders( Arrays.asList( "waterview/template/" ) );
        rendererMap.put( "vm", new VelocityRenderer( veb.newEngine() ) );
        Renderer renderer = new ExtensionBasedRenderer( rendererMap );
        List<Valve> valves = new ArrayList<Valve>();
        valves.add( new ParseRequestPathValve( "/index.vm" ) );
        valves.add( new RenderPageValve( moduleResolver, renderer, "_layout_/default_layout.vm" ) );
        pipeline = new Pipeline( valves );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void process( WebContext context )
        throws IOException
    {
        pipeline.invoke( context );
    }
}
