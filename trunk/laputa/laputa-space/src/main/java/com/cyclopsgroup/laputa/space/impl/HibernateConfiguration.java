package com.cyclopsgroup.laputa.space.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.space.pojo.SpacePage;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;
import com.cyclopsgroup.laputa.space.pojo.SpaceViewlet;

public class HibernateConfiguration
    extends AnnotationConfiguration
{
    private static final long serialVersionUID = 1L;

    public HibernateConfiguration()
    {
        addAnnotatedClass( SpaceServiceContext.class );
        addAnnotatedClass( SpacePage.class );
        addAnnotatedClass( SpaceViewlet.class );
    }
}
