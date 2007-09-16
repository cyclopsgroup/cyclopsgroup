package com.cyclopsgroup.laputa;

public final class WidgetType
{
    private final String name;

    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public WidgetType( String name )
    {
        this.name = name;
    }
}
