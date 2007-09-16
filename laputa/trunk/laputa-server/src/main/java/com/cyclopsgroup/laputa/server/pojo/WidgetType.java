package com.cyclopsgroup.laputa.server.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "cg_laputa_widget_type" )
public class WidgetType
{
    private String description;

    private String providerClassName;

    private WidgetTypeState state;

    private String title;

    private long typeId;

    private String typeName;

    public String getDescription()
    {
        return description;
    }

    public String getProviderClassName()
    {
        return providerClassName;
    }

    public WidgetTypeState getState()
    {
        return state;
    }

    public String getTitle()
    {
        return title;
    }

    public long getTypeId()
    {
        return typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setProviderClassName( String providerClassName )
    {
        this.providerClassName = providerClassName;
    }

    public void setState( WidgetTypeState state )
    {
        this.state = state;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setTypeId( long typeId )
    {
        this.typeId = typeId;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }
}
