package org.cyclopsgroup.waterview.impl.module;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.Mapping;
import org.cyclopsgroup.waterview.Page;

/**
 * This implementation collects a given collection of module objects, POJO annotated with {@link Mapping} annotation, and
 * build a map of {@link WebModule} objects based on annotation parameters.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CollectionModuleResolver
    implements ModuleResolver
{
    private final Map<String, WebModule> webModules;

    /**
     * @param modules Given collection of all exiting module instances
     */
    public CollectionModuleResolver( Collection<Object> modules )
    {
        Validate.notNull( modules, "Modules can't be NULL" );
        HashMap<String, WebModule> ms = new HashMap<String, WebModule>( modules.size() );
        for ( Object module : modules )
        {
            for(Method method : module.getClass().getMethods())
            {
                if(!method.isAnnotationPresent( Mapping.class ))
                {
                    continue;
                }
                WebModuleAdapter adapter;
                if ( method.isAnnotationPresent( Page.class ) )
                {
                    adapter = new PageModuleAdapter( module, method );
                }
                else
                {
                    adapter = new WebModuleAdapter( module, method );
                }
                ms.put( adapter.getDefinition().path(), adapter );
            }
        }
        webModules = Collections.unmodifiableMap( ms );
    }

    /**
     * @param modules Arrays of modules
     * @see #CollectionModuleResolver(Collection)
     */
    public CollectionModuleResolver( Object... modules )
    {
        this( Arrays.asList( modules ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public WebModule findModule( String name )
    {
        return webModules.get( name );
    }
}
