package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Content object
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement(name="Content")
public class Content
{
    @XmlElement
    private String description;

    @XmlElement
    private long lastUpdated;

    @XmlElement
    private String name;

    @XmlElement
    private long size;
    
    /**
     * @return Value of field description
     */
    public final String getDescription()
    {
        return description;
    }
    
    /**
     * @return Last update timestamp
     */
    public final long getLastUpdated()
    {
        return lastUpdated;
    }

    /**
     * @return Name of content
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @return
     */
    public final long getSize()
    {
        return size;
    }

    /**
     * @param description Value of field description to set
     */
    public final void setDescription( String description )
    {
        this.description = description;
    }

    public final void setLastUpdated( long lastUpdated )
    {
        this.lastUpdated = lastUpdated;
    }

    public final void setName( String name )
    {
        this.name = name;
    }

    public final void setSize( long size )
    {
        this.size = size;
    }
}
