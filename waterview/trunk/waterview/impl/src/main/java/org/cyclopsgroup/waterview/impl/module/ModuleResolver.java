package org.cyclopsgroup.waterview.impl.module;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.spi.ComponentResolver;

/**
 * Interface to resolve module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ModuleResolver
{
    private final ComponentResolver resolver;

    public ModuleResolver( ComponentResolver resolver )
    {
        Validate.notNull( resolver, "Component resover can't be NULL" );
        this.resolver = resolver;
    }

    /**
     * Find module mapped to given name
     * 
     * @param name Module name
     * @return Module class or null if not found
     */
    public WebModule findModule( String name )
    {
        return new WebModuleAdapter( resolver.findComponent( Object.class, name ) );
    }
}
