package com.cyclopsgroup.arapaho.hibernate;

import org.hibernate.cfg.Configuration;

public final class HibernateUtils
{
    public static void makeHsqlConfiguration( Configuration c )
    {
        c.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
        c.setProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" );
        c.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        c.setProperty( "hibernate.connection.username", "sa" );
        c.setProperty( "hibernate.connection.pool_size", "1" );
        c.setProperty( "hibernate.connection.autocommit", "true" );
        c.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider" );
        c.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        c.setProperty( "hibernate.show_sql", "true" );
    }
}
