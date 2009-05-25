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

    /**
     * @return A verbal description of repository
     */
    public final String getDescription()
    {
        return description;
    }

    /**
     * @return Unique name of repository
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @return Root folder in repository
     */
    public final Folder getRoot()
    {
        return root;
    }

    /**
     * @return Full URI expression
     */
    public final String getUri()
    {
        return uri;
    }

    /**
     * @param description Verbal description of repository
     */
    public final void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @param name Unique name of repository
     */
    public final void setName( String name )
    {
        this.name = name;
    }

    /**
     * @param root Root folder
     */
    public final void setRoot( Folder root )
    {
        this.root = root;
    }

    /**
     * @param uri URI expresion of repository
     */
    public final void setUri( String uri )
    {
        this.uri = uri;
    }
}
