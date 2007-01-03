package com.cyclopsgroup.arapaho.hibernate;

import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.collections.ExtendedProperties;

public class HibernateInstanceTest
    extends TestCase
{
    public void testDummy()
        throws Exception
    {
        Properties p = new Properties();
        p.load( getClass().getResourceAsStream( "test.properties" ) );
        ExtendedProperties ep = ExtendedProperties.convertProperties( p );
        System.out.println( ep );
    }
}
