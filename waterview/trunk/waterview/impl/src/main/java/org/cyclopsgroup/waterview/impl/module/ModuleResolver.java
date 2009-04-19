package org.cyclopsgroup.waterview.impl.module;

/**
 * Interface to resolve module From this interface caller can pull out a {@link WebModule} implementation for given name
 * instance of module. The implementation determines how to provide module instance based on given name.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ModuleResolver
{
    /**
     * Find module instance mapped to given name
     * 
     * @param name Module name
     * @return Module class or null if not found
     */
    WebModule findModule( String name );
}
