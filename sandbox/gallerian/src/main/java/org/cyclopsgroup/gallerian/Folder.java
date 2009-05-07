package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Folder")
public class Folder
{
    @XmlElement
    private String name;
    
    @XmlElement
    private String path;

    public final String getName()
    {
        return name;
    }

    public final void setName( String name )
    {
        this.name = name;
    }

    public final String getPath()
    {
        return path;
    }

    public final void setPath( String path )
    {
        this.path = path;
    }
}
