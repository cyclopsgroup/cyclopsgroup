package org.cyclopsgroup.larc.api;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Event entity object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement( name = "Event" )
public class Event
{
    @XmlElement
    private String description;
    @XmlElement
    private String id;
    @XmlElement
    private List<String> tags = Collections.emptyList();
    @XmlElement
    private String title;

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
    public final String getId()
    {
        return id;
    }

    /**
     * @return Value of field tags
     */
    public final List<String> getTags()
    {
        return tags;
    }

    /**
     * @return Value of field title
     */
    public final String getTitle()
    {
        return title;
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
    public final void setId( String id )
    {
        this.id = id;
    }

    /**
     * @param tags Value of field tags to set
     */
    public final void setTags( List<String> tags )
    {
        this.tags = tags;
    }

    /**
     * @param title Value of field title to set
     */
    public final void setTitle( String title )
    {
        this.title = title;
    }
}
