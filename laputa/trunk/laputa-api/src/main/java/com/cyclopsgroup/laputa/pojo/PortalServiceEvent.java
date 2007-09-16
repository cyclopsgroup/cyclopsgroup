package com.cyclopsgroup.laputa.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "cg_laputa_service_event" )
public class PortalServiceEvent
{
    private String comment;

    private Date eventDate;

    private long eventId;

    private String modiferId;

    private ServiceState newState;

    private PortalService portalService;

    private ServiceState previousState;

    public String getComment()
    {
        return comment;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public long getEventId()
    {
        return eventId;
    }

    public String getModiferId()
    {
        return modiferId;
    }

    public ServiceState getNewState()
    {
        return newState;
    }

    public PortalService getPortalService()
    {
        return portalService;
    }

    public ServiceState getPreviousState()
    {
        return previousState;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public void setEventDate( Date eventDate )
    {
        this.eventDate = eventDate;
    }

    public void setEventId( long eventId )
    {
        this.eventId = eventId;
    }

    public void setModiferId( String modiferId )
    {
        this.modiferId = modiferId;
    }

    public void setNewState( ServiceState newState )
    {
        this.newState = newState;
    }

    public void setPortalService( PortalService portalService )
    {
        this.portalService = portalService;
    }

    public void setPreviousState( ServiceState previousState )
    {
        this.previousState = previousState;
    }
}
