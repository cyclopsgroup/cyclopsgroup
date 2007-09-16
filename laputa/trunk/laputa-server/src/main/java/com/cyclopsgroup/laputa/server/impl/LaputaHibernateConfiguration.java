package com.cyclopsgroup.laputa.server.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.server.pojo.PortalService;
import com.cyclopsgroup.laputa.server.pojo.PortalServiceEvent;
import com.cyclopsgroup.laputa.server.pojo.Widget;
import com.cyclopsgroup.laputa.server.pojo.WidgetEvent;
import com.cyclopsgroup.laputa.server.pojo.WidgetTemplate;
import com.cyclopsgroup.laputa.server.pojo.WidgetType;
import com.cyclopsgroup.laputa.widget.raw.RawWidgetDetail;

public class LaputaHibernateConfiguration
    extends AnnotationConfiguration
{
    private static final long serialVersionUID = 1L;

    public LaputaHibernateConfiguration()
    {
        addAnnotatedClass( WidgetType.class );
        addAnnotatedClass( WidgetTemplate.class );
        addAnnotatedClass( PortalService.class );
        addAnnotatedClass( PortalServiceEvent.class );
        addAnnotatedClass( Widget.class );
        addAnnotatedClass( WidgetEvent.class );
        addAnnotatedClass( RawWidgetDetail.class );
    }
}
