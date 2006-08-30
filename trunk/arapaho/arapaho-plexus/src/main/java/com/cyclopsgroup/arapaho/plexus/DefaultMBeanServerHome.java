package com.cyclopsgroup.arapaho.plexus;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;

public class DefaultMBeanServerHome
    extends AbstractLogEnabled
    implements MBeanServerHome, Initializable, Configurable
{
    public String getDomain()
    {
        return domain;
    }

    private MBeanServer mbeanServer;

    private String domain;

    public MBeanServer getMBeanServer()
    {
        return mbeanServer;
    }

    public void initialize()
        throws Exception
    {
        mbeanServer = MBeanServerFactory.newMBeanServer( domain );
    }

    public void configure( Configuration conf )
        throws ConfigurationException
    {
        domain = conf.getChild( "domain" ).getValue( "plexus" );
    }
}
