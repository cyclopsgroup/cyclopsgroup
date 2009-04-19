package org.cyclopsgroup.waterview.impl.module;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.Page;

/**
 * This implementation collects a given collection of module objects, POJO annotated with {@link Module} annotation, and
 * build a map of {@link WebModule} objects based on annotation parameters.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CollectionModuleResolver
    implements ModuleResolver
{
    private static final Log LOG = LogFactory.getLog( CollectionModuleResolver.class );

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
            if ( !module.getClass().isAnnotationPresent( Module.class ) )
            {
                LOG.warn( "Module " + module + " is not annotated with " + Module.class );
                continue;
            }
            WebModuleAdapter adapter;
            if ( module.getClass().isAnnotationPresent( Page.class ) )
            {
                adapter = new PageModuleAdapter( module );
            }
            else
            {
                adapter = new WebModuleAdapter( module );
            }
            ms.put( adapter.getDefinition().path(), adapter );
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
