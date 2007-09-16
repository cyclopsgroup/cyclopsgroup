package com.cyclopsgroup.laputa.widget.raw;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "cg_laputa_raw_widget_event" )
public class RawWidgetDetailEvent
{
    private long eventId;

    private int newVersion;

    private int previousVersion;

    private String content;

    private Date eventDate;

    private String comment;

    private String modifierId;

    public long getEventId()
    {
        return eventId;
    }

    public void setEventId( long eventId )
    {
        this.eventId = eventId;
    }

    public int getNewVersion()
    {
        return newVersion;
    }

    public void setNewVersion( int newVersion )
    {
        this.newVersion = newVersion;
    }

    public int getPreviousVersion()
    {
        return previousVersion;
    }

    public void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate( Date eventDate )
    {
        this.eventDate = eventDate;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public String getModifierId()
    {
        return modifierId;
    }

    public void setModifierId( String modifierId )
    {
        this.modifierId = modifierId;
    }
}
