package org.cyclopsgroup.waterview;

import java.util.HashMap;
import java.util.Map;

/**
 * A suite of web modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class WebModuleSuite
{
    private final Map<String, Class<? extends WebModule>> moduleTypes =
        new HashMap<String, Class<? extends WebModule>>();

    /**
     * @param path Path of module
     * @return Module type
     */
    public final Class<? extends WebModule> getModuleType( String path )
    {
        return moduleTypes.get( path );
    }

    /**
     * @param path Path of the module
     * @return True if there's a module for given path
     */
    public final boolean containsModuleFor( String path )
    {
        return moduleTypes.containsKey( path );
    }

    /**
     * @param path Path of the module
     * @param moduleType Module type
     */
    protected final void addModuleType( String path, Class<? extends WebModule> moduleType )
    {
        moduleTypes.put( path, moduleType );
    }

    /**
     * Initialize the module suite
     */
    protected abstract void init();
}
