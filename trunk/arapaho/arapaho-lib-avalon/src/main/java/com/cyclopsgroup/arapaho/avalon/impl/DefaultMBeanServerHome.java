package com.cyclopsgroup.arapaho.avalon.impl;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.arapaho.avalon.MBeanServerHome;

/**
 * Default implementation of MBeanServerHome where MBeanServer instance is simply created from factory
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultMBeanServerHome
    extends AbstractLogEnabled
    implements MBeanServerHome, Initializable, Configurable
{
    private String domain;

    private MBeanServer mbeanServer;

    /**
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        domain = conf.getChild( "domain" ).getValue( "plexus-container" );
    }

    public String getDomain()
    {
        return domain;
    }

    public MBeanServer getMBeanServer()
    {
        return mbeanServer;
    }

    /**
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        mbeanServer = MBeanServerFactory.createMBeanServer( domain );
    }
}
