package com.cyclopsgroup.laputa.server.pojo;

public class WidgetTemplate
{
    private long templateId;

    private WidgetType widgetType;

    private String userId;

    private String title;

    private String description;

    public long getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId( long templateId )
    {
        this.templateId = templateId;
    }

    public WidgetType getWidgetType()
    {
        return widgetType;
    }

    public void setWidgetType( WidgetType widgetType )
    {
        this.widgetType = widgetType;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }
}
