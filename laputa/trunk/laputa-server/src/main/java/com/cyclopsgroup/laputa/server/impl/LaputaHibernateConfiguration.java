package com.cyclopsgroup.laputa.server.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.server.pojo.PortalService;
import com.cyclopsgroup.laputa.server.pojo.PortalServiceEvent;
import com.cyclopsgroup.laputa.server.pojo.UserWidget;
import com.cyclopsgroup.laputa.server.pojo.UserWidgetEvent;
import com.cyclopsgroup.laputa.widget.raw.RawWidgetDetail;

public class LaputaHibernateConfiguration
    extends AnnotationConfiguration
{
    private static final long serialVersionUID = 1L;

    public LaputaHibernateConfiguration()
    {
        addAnnotatedClass( PortalService.class );
        addAnnotatedClass( PortalServiceEvent.class );
        addAnnotatedClass( UserWidget.class );
        addAnnotatedClass( UserWidgetEvent.class );
        addAnnotatedClass( RawWidgetDetail.class );
    }
}
