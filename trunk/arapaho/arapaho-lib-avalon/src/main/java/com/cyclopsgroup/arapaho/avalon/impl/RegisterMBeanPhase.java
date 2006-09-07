package com.cyclopsgroup.arapaho.avalon.impl;

import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.component.manager.ComponentManager;
import org.codehaus.plexus.lifecycle.phase.AbstractPhase;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.PhaseExecutionException;

import com.cyclopsgroup.arapaho.avalon.MBeanClass;
import com.cyclopsgroup.arapaho.avalon.MBeanServerHome;

/**
 * Plexus phase where avalon components are registered as MBean
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class RegisterMBeanPhase
    extends AbstractPhase
{
    private Log log = LogFactory.getLog( getClass() );

    @Override
    public void execute( Object component, ComponentManager manager )
        throws PhaseExecutionException
    {
        String role = manager.getComponentDescriptor().getRole();
        if ( role.equals( MBeanServerHome.ROLE ) )
        {
            return;
        }

        String componentKey = manager.getComponentDescriptor().getComponentKey();
        try
        {
            MBeanServerHome mbeanServerHome = (MBeanServerHome) manager.getContainer().lookup( MBeanServerHome.ROLE );
            MBeanServer mbeanServer = mbeanServerHome.getMBeanServer();

            ObjectName name = new ObjectName( mbeanServerHome.getDomain(), "key", componentKey );
            if ( component instanceof DynamicMBean )
            {
                log.debug( "Register " + componentKey + " to mbean server directly since it implements DynamicMBean:"
                    + component );
                mbeanServer.registerMBean( component, name );
            }
            else if ( component.getClass().getAnnotation( MBeanClass.class ) != null )
            {
                log.debug( "Register " + componentKey + " to mbean server through an adapter" );

                mbeanServer.registerMBean( new DynamicMBeanAdapter( component, componentKey ), name );
            }
        }
        catch ( Exception e )
        {
            log.warn( "Can't register mbean " + component + ", key=" + componentKey, e );
        }
    }
}
