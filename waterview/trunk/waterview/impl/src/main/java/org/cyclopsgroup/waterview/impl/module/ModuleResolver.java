package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.WebModule;

public interface ModuleResolver
{
    WebModule findModule( String name );

    String findResource( String path );
}
