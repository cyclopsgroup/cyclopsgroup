package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Repository object
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement( name = "ContentRepository" )
public class ContentRepository
{
    @XmlElement
    private String description;

    @XmlElement
    private String name;
    
    @XmlElement
    private Folder root;
    
    @XmlElement
    private String uri;

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

    public final String getUri()
    {
        return uri;
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

    public final void setUri( String uri )
    {
        this.uri = uri;
    }
}
