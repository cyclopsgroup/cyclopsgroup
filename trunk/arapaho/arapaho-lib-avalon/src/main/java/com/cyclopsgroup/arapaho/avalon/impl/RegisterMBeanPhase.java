package com.cyclopsgroup.arapaho.avalon.impl;

import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.component.manager.ComponentManager;
import org.codehaus.plexus.lifecycle.phase.AbstractPhase;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.PhaseExecutionException;

import com.cyclopsgroup.arapaho.avalon.MBeanServerHome;

public class RegisterMBeanPhase
    extends AbstractPhase
{
    private Log log = LogFactory.getLog( getClass() );

    @Override
    public void execute( Object component, ComponentManager manager )
        throws PhaseExecutionException
    {
        String role = manager.getComponentDescriptor().getRole();
        if ( role.equals( MBeanServerHome.ROLE ) || role.equals( MBeanRegistry.ROLE ) )
        {
            return;
        }

        String componentKey = manager.getComponentDescriptor().getComponentKey();
        try
        {
            MBeanServerHome mbeanServerHome = (MBeanServerHome) manager.getContainer().lookup( MBeanServerHome.ROLE );
            MBeanServer mbeanServer = mbeanServerHome.getMBeanServer();

            if ( component instanceof DynamicMBean )
            {
                log.debug( "Register " + componentKey + " to mbean server directly since it implements DynamicMBean:"
                    + component );
                mbeanServer
                    .registerMBean( component, new ObjectName( mbeanServerHome.getDomain(), "key", componentKey ) );
            }
            else
            {
                MBeanRegistry registry = (MBeanRegistry) manager.getContainer().lookup( MBeanRegistry.ROLE );
                registry.register( component, componentKey );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            log.warn( "Can't register mbean " + component + ", key=" + componentKey, e );
        }
    }
}
