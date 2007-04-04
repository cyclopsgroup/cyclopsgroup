package com.cyclopsgroup.laputa.space.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.space.pojo.SpacePage;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;
import com.cyclopsgroup.laputa.space.pojo.SpaceViewlet;

public class HibernateConfiguration
    extends AnnotationConfiguration
{
    public HibernateConfiguration()
    {
        addAnnotatedClass( SpaceServiceContext.class );
        addAnnotatedClass( SpacePage.class );
        addAnnotatedClass( SpaceViewlet.class );
    }
}
