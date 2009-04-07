package org.cyclopsgroup.waterview.impl.module;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * Interface to resolve module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CollectionModuleResolver implements ModuleResolver
{
    private final Map<String, WebModule> webModules;

    /**
     * @param modules Collection of all modules
     */
    public CollectionModuleResolver( Collection<Object> modules )
    {
        Validate.notNull( modules, "Modules can't be NULL" );
        HashMap<String, WebModule> ms = new HashMap<String, WebModule>(modules.size());
        for(Object module: modules)
        {
            WebModuleAdapter adapter = new WebModuleAdapter(module);
            ms.put( adapter.getDefinition().path(), adapter );
        }
        webModules = Collections.unmodifiableMap( ms );
    }
    
    /**
     * @param modules Arrays of modules
     */
    public CollectionModuleResolver(Object...modules)
    {
        this(Arrays.asList( modules ));
    }

    /**
     * Find module mapped to given name
     * 
     * @param name Module name
     * @return Module class or null if not found
     */
    public WebModule findModule( String name )
    {
        return webModules.get( name );
    }
}
