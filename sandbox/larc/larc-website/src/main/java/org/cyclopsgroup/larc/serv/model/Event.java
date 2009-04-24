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
    @Column( name = "id", length = 32 )
    @Id
    private String id;

    @Column( name = "title", length = 255, nullable = false )
    private String title;

    @Column( name = "description", length = 4000 )
    private String description;

    @Column( name = "location", length = 255 )
    private String location;

    @OneToOne( optional = false )
    @JoinColumn( name = "secure_entity_id" )
    private SecureEntity secureEntity;

    @Column( name = "event_type", nullable = false )
    @Enumerated
    private EventType eventType = EventType.ONCE;

    @Column( name = "event_state", nullable = false )
    @Enumerated
    private EventState eventState = EventState.PENDING;
}
