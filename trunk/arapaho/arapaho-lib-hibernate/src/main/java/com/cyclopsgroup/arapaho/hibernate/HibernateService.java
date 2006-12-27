package com.cyclopsgroup.arapaho.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface HibernateService
{
    Configuration getConfiguration();

    SessionFactory createSessionFactory();
}