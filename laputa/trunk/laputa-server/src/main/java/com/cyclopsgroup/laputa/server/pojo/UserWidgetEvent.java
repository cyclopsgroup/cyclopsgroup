package com.cyclopsgroup.laputa.server.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserWidgetEvent
{
    @Column( name = "description", length = 512 )
    private String description;

    @Id
    @Column( name = "event_id" )
    private long eventId;

    @Column( name = "language", nullable = false, length = 8 )
    private String language;

    @Column( name = "new_version", nullable = false )
    private int newVersion;

    @Column( name = "previous_version", nullable = false )
    private int previousVersion;

    @Column( name = "title", nullable = false, length = 64 )
    private String title;

    private UserWidget userWidget;

    @Column( name = "widget_detail", length = 256 )
    private String widgetDetail;

    public String getDescription()
    {
        return description;
    }

    public long getEventId()
    {
        return eventId;
    }

    public String getLanguage()
    {
        return language;
    }

    public int getNewVersion()
    {
        return newVersion;
    }

    public int getPreviousVersion()
    {
        return previousVersion;
    }

    public String getTitle()
    {
        return title;
    }

    public UserWidget getUserWidget()
    {
        return userWidget;
    }

    public String getWidgetDetail()
    {
        return widgetDetail;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setEventId( long eventId )
    {
        this.eventId = eventId;
    }

    public void setLanguage( String language )
    {
        this.language = language;
    }

    public void setNewVersion( int newVersion )
    {
        this.newVersion = newVersion;
    }

    public void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setUserWidget( UserWidget userWidget )
    {
        this.userWidget = userWidget;
    }

    public void setWidgetDetail( String widgetDetail )
    {
        this.widgetDetail = widgetDetail;
    }
}
