package com.cyclopsgroup.laputa.space.impl.hibernate;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.space.pojo.SpacePage;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;
import com.cyclopsgroup.laputa.space.pojo.SpaceViewlet;

public class SpaceConfiguration
    extends AnnotationConfiguration
{
    public SpaceConfiguration()
    {
        addAnnotatedClass( SpaceViewlet.class );
        addAnnotatedClass( SpacePage.class );
        addAnnotatedClass( SpaceServiceContext.class );
    }
}
