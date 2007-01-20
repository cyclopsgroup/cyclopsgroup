package com.cyclopsgroup.arapaho.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cg_arapaho_tentity", uniqueConstraints = { @UniqueConstraint(columnNames = { "entity_id" }) })
public class TEntity
{
    private boolean disabled;

    private long id;

    private String name;

    public TEntity()
    {

    }

    public TEntity( long id, String name )
    {
        this.id = id;
        this.name = name;
    }

    @Column(name = "entity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId()
    {
        return id;
    }

    @Column(name = "entity_name", nullable = false, length = 30)
    public String getName()
    {
        return name;
    }

    @Column
    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
