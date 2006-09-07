package com.cyclopsgroup.arapaho.avalon;

import javax.management.MBeanServer;

/**
 * Home of MBeanServer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public interface MBeanServerHome
{
    String ROLE = MBeanServerHome.class.getName();

    MBeanServer getMBeanServer();

    String getDomain();
}
