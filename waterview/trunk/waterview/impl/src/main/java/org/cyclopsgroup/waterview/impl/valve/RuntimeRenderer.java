package org.cyclopsgroup.waterview.impl.valve;

import org.cyclopsgroup.waterview.RenderableModule;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.render.Renderer;

public class RuntimeRenderer
{
    private final WebContext context;

    private final Renderer renderer;

    private final ModuleResolver resolver;

    public RuntimeRenderer( Renderer renderer, ModuleResolver resolver, WebContext context )
    {
        this.renderer = renderer;
        this.context = context;
        this.resolver = resolver;
    }

    public void render( String name )
    {
        RenderableModule module = resolver.findRenderableModule( name );
        WebContext childContext = new ChildWebContext( context );
        module.beforeRender( childContext );
        childContext.setVariable( "renderer", new RuntimeRenderer( renderer, resolver, childContext ) );
    }
}
