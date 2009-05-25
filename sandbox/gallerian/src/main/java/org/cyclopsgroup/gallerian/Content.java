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
    private String mimeType;

    @XmlElement
    private String name;

    @XmlElement
    private boolean previewable;

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
     * @return Value of field mimeType
     */
    public final String getMimeType()
    {
        return mimeType;
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
     * @return Value of field previewable
     */
    public final boolean isPreviewable()
    {
        return previewable;
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
     * @param mimeType Value of field mimeType to set
     */
    public final void setMimeType( String mimeType )
    {
        this.mimeType = mimeType;
    }

    /**
     * @param name Base name of content
     */
    public final void setName( String name )
    {
        this.name = name;
    }

    /**
     * @param previewable Value of field previewable to set
     */
    public final void setPreviewable( boolean previewable )
    {
        this.previewable = previewable;
    }

    /**
     * @param size Number of bytes in content
     */
    public final void setSize( long size )
    {
        this.size = size;
    }
}
