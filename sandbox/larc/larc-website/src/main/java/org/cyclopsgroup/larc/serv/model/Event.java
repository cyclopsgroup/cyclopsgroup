package org.cyclopsgroup.larc.serv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "larc_event", uniqueConstraints = { @UniqueConstraint( columnNames = { "secure_entity_id" } ) } )
public class Event
{
    @Column( name = "description", length = 4000 )
    private String description;

    @Column( name = "event_state", nullable = false )
    @Enumerated
    private EventState eventState = EventState.PENDING;

    @Column( name = "event_type", nullable = false )
    @Enumerated
    private EventType eventType = EventType.ONCE;

    @Column( name = "id", length = 32 )
    @Id
    private String id;

    @Column( name = "location", length = 255 )
    private String location;

    @OneToOne( optional = false )
    @JoinColumn( name = "secure_entity_id" )
    private SecureEntity secureEntity;

    @Column( name = "title", length = 255, nullable = false )
    private String title;

    public final String getDescription()
    {
        return description;
    }

    public final EventState getEventState()
    {
        return eventState;
    }

    public final EventType getEventType()
    {
        return eventType;
    }

    public final String getId()
    {
        return id;
    }

    public final String getLocation()
    {
        return location;
    }

    public final SecureEntity getSecureEntity()
    {
        return secureEntity;
    }

    public final String getTitle()
    {
        return title;
    }

    public final void setDescription( String description )
    {
        this.description = description;
    }

    public final void setEventState( EventState eventState )
    {
        this.eventState = eventState;
    }

    public final void setEventType( EventType eventType )
    {
        this.eventType = eventType;
    }

    public final void setId( String id )
    {
        this.id = id;
    }

    public final void setLocation( String location )
    {
        this.location = location;
    }

    public final void setSecureEntity( SecureEntity secureEntity )
    {
        this.secureEntity = secureEntity;
    }

    public final void setTitle( String title )
    {
        this.title = title;
    }
}
