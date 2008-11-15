package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.ActionModule;
import org.cyclopsgroup.waterview.RenderableModule;

public interface ModuleResolver
{
    RenderableModule findRenderableModule( String name );

    ActionModule findActionModule( String name );
}
