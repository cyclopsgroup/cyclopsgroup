package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.WebModule;

/**
 * Interface to resolve module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ModuleResolver
{
    /**
     * Find module mapped to given name
     * 
     * @param name Module name
     * @return Module class or null if not found
     */
    WebModule findModule( String name );
}
