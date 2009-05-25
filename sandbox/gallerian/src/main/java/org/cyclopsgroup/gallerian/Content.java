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
    private ContentId id;

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
     * @return Value of field id
     */
    public final ContentId getId()
    {
        return id;
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
     * @return Number of bytes in this content
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

    /**
     * @param id Value of field id to set
     */
    public final void setId( ContentId id )
    {
        this.id = id;
    }

    /**
     * @param lastUpdated Timestamp of last update
     */
    public final void setLastUpdated( long lastUpdated )
    {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @param name Base name of content
     */
    public final void setName( String name )
    {
        this.name = name;
    }

    /**
     * @param size Number of bytes in content
     */
    public final void setSize( long size )
    {
        this.size = size;
    }
}
