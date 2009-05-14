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
    private long lastUpdated;

    @XmlElement
    private String name;
    
    @XmlElement
    private long size;

    public final long getLastUpdated()
    {
        return lastUpdated;
    }

    public final String getName()
    {
        return name;
    }

    public final long getSize()
    {
        return size;
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
