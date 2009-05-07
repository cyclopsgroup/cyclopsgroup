package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "ContentRepository" )
public class ContentRepository
{
    @XmlElement
    private String description;

    @XmlElement
    private String name;
    
    @XmlElement
    private Folder root;

    public final String getDescription()
    {
        return description;
    }

    public final String getName()
    {
        return name;
    }

    public final Folder getRoot()
    {
        return root;
    }

    public final void setDescription( String description )
    {
        this.description = description;
    }

    public final void setName( String name )
    {
        this.name = name;
    }

    public final void setRoot( Folder root )
    {
        this.root = root;
    }
}
