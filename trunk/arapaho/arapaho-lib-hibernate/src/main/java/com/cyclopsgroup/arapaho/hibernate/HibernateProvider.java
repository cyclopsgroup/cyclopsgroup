package com.cyclopsgroup.arapaho.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface HibernateProvider
{
    Configuration getConfiguration();

    SessionFactory createSessionFactory();
}