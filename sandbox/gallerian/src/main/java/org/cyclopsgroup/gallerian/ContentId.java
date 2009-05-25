package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Identifier of content which provides enough information to locate a content
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement( name = "Content" )
public class ContentId
{
    /**
     * Default constructor called by JAXB 
     */
    public ContentId()
    {
    }
    
    /**
     * Constructor with given path and repository
     * 
     * @param path Path of content starting with slash
     * @param repositoryName Name of repository
     */
    public ContentId(String path, String repositoryName)
    {
        this.path = path;
        this.repositoryName = repositoryName;
    }

    @XmlElement
    private String path;

    @XmlElement
    private String repositoryName;

    /**
     * @return Value of field path
     */
    public final String getPath()
    {
        return path;
    }

    /**
     * @return Value of field repositoryName
     */
    public final String getRepositoryName()
    {
        return repositoryName;
    }

    /**
     * @param path Value of field path to set
     */
    public final void setPath( String path )
    {
        this.path = path;
    }

    /**
     * @param repositoryName Value of field repositoryName to set
     */
    public final void setRepositoryName( String repositoryName )
    {
        this.repositoryName = repositoryName;
    }
}
