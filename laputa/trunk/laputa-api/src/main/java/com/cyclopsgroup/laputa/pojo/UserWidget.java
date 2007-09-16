package com.cyclopsgroup.laputa.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "cg_laputa_user_widget" )
public class UserWidget
{
    @Column( name = "created_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date createdDate;

    @Column( name = "description", length = 512 )
    private String description;

    @Column( name = "language", nullable = false, length = 8 )
    private String language;

    @Column( name = "last_modified_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastModifiedDate;

    private PortalService portalService;

    @Column( name = "title", nullable = false, length = 64 )
    private String title;

    @Column( name = "widget_detail", length = 256 )
    private String widgetDetail;

    @Id
    @Column( name = "widget_id" )
    private long widgetId;

    @Column( name = "widget_type", nullable = false, length = 32 )
    private String widgetType;

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLanguage()
    {
        return language;
    }

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public PortalService getPortalService()
    {
        return portalService;
    }

    public String getTitle()
    {
        return title;
    }

    public String getWidgetDetail()
    {
        return widgetDetail;
    }

    public long getWidgetId()
    {
        return widgetId;
    }

    public String getWidgetType()
    {
        return widgetType;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setLanguage( String language )
    {
        this.language = language;
    }

    public void setLastModifiedDate( Date lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setPortalService( PortalService portalService )
    {
        this.portalService = portalService;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setWidgetDetail( String widgetDetail )
    {
        this.widgetDetail = widgetDetail;
    }

    public void setWidgetId( long widgetId )
    {
        this.widgetId = widgetId;
    }

    public void setWidgetType( String widgetType )
    {
        this.widgetType = widgetType;
    }
}
