package org.cyclopsgroup.waterview.impl.render;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.WebModule;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.ipa.Renderer;

/**
 * Renderer used in pages
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RuntimeRenderer
{
    /**
     * Name in context
     */
    public static final String NAME = "renderer";

    private final WebContext context;

    private final Renderer renderer;

    private final ModuleResolver resolver;

    /**
     * @param renderer
     * @param resolver
     * @param context
     */
    public RuntimeRenderer( Renderer renderer, ModuleResolver resolver, WebContext context )
    {
        this.renderer = renderer;
        this.context = context;
        this.resolver = resolver;
    }

    /**
     * @param name Name of view to render
     */
    public void render( String name )
    {
        WebModule module = resolver.findModule( name );
        WebContext childContext = new ChildWebContext( context );
        module.beforeRender( childContext );
        childContext.setVariable( NAME, new RuntimeRenderer( renderer, resolver, childContext ) );
    }
}
