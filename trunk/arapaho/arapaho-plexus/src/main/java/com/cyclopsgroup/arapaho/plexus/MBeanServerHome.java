package com.cyclopsgroup.arapaho.plexus;

import javax.management.MBeanServer;

public interface MBeanServerHome
{
    String ROLE = MBeanServerHome.class.getName();

    MBeanServer getMBeanServer();

    String getDomain();
}
