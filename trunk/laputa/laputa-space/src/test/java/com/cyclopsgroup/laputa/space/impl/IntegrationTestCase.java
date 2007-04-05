package com.cyclopsgroup.laputa.space.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.arapaho.spring.ApplicationContextTestCase;
import com.cyclopsgroup.laputa.space.pojo.SpacePage;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;
import com.cyclopsgroup.laputa.space.pojo.SpaceViewlet;

public class IntegrationTestCase
    extends ApplicationContextTestCase
{
    @Override
    protected AnnotationConfiguration createHibernateConfiguration()
    {
        AnnotationConfiguration c = super.createHibernateConfiguration();
        c.addAnnotatedClass( SpaceServiceContext.class );
        c.addAnnotatedClass( SpacePage.class );
        c.addAnnotatedClass( SpaceViewlet.class );
        return c;
    }
}
