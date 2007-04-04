package com.cyclopsgroup.laputa.space.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cglpt_spcpg")
public class SpacePage
{
    private String description;

    private boolean hidden;

    private long id;

    private int position;

    private SpaceServiceContext serviceContext;

    private String title;

    @Column(name = "description", length = 1024)
    public String getDescription()
    {
        return description;
    }

    @Column(name = "page_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId()
    {
        return id;
    }

    @Column(name = "position", nullable = false)
    public int getPosition()
    {
        return position;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_context_id", updatable = false)
    public SpaceServiceContext getServiceContext()
    {
        return serviceContext;
    }

    @Column(name = "title", nullable = false, length = 255)
    public String getTitle()
    {
        return title;
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setHidden( boolean hidden )
    {
        this.hidden = hidden;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public void setPosition( int position )
    {
        this.position = position;
    }

    public void setServiceContext( SpaceServiceContext serviceContext )
    {
        this.serviceContext = serviceContext;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }
}
