package com.cyclopsgroup.arapaho.plexus;

import java.util.HashSet;
import java.util.Set;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.modeler.Registry;

public class MBeanRegistry
    extends AbstractLogEnabled
{
    public static final String ROLE = MBeanRegistry.class.getName();

    private Registry registry = new Registry();

    private Set<String> unknownPackages = new HashSet<String>();

    public void register( Object component, String componentKey )
        throws Exception
    {
        String pkg = component.getClass().getPackage().getName();
        synchronized ( this )
        {
            if ( !unknownPackages.contains( pkg ) )
            {
                registry.loadDescriptors( pkg, Thread.currentThread().getContextClassLoader() );
                unknownPackages.add( pkg );
            }
        }
        registry.findManagedBean( component.getClass(), "" );
    }

}
