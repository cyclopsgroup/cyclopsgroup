package com.cyclopsgroup.laputa.server.impl;

import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.pojo.PortalService;
import com.cyclopsgroup.laputa.pojo.PortalServiceEvent;
import com.cyclopsgroup.laputa.pojo.UserWidget;
import com.cyclopsgroup.laputa.widget.raw.RawWidgetDetail;
import com.cyclopsgroup.laputa.widget.raw.RawWidgetDetailEvent;

public class LaputaHibernateConfiguration
    extends AnnotationConfiguration
{
    private static final long serialVersionUID = 1L;

    public LaputaHibernateConfiguration()
    {
        addAnnotatedClass( PortalService.class );
        addAnnotatedClass( PortalServiceEvent.class );
        addAnnotatedClass( UserWidget.class );
        addAnnotatedClass( RawWidgetDetail.class );
        addAnnotatedClass( RawWidgetDetailEvent.class );
    }
}
