package org.cyclopsgroup.waterview.impl.module;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebModule;

/**
 * Module resolver based on a list of packages
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class PackageListModuleResolver
    implements ModuleResolver
{
    private final ClassLoader classLoader;

    /**
     * @param packageNames List of package names
     */
    public PackageListModuleResolver( List<String> packageNames )
    {
        this( packageNames, PackageListModuleResolver.class.getClassLoader() );
    }

    /**
     * @param packageNames Name of package names
     * @param classLoader Class loader that loads resources
     */
    public PackageListModuleResolver( List<String> packageNames, ClassLoader classLoader )
    {
        Validate.notNull( classLoader, "Class loader can't be NULL" );
        this.classLoader = classLoader;
    }

    /**
     * @inheritDoc
     */
    @Override
    public WebModule findModule( String name )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
