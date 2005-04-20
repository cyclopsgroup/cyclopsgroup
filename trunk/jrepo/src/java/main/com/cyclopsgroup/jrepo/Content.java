/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.jrepo;

import java.util.Date;

/**
 * Content could be any java object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Content
{
    /** Empty array */
    public static final Content[] EMPTY_ARRAY = new Content[0];

    private String contentType;

    private Date createdTime;

    private long id;

    private Date lastModified;

    private PropertyManager propertyManager;

    private transient Repository repository;

    private String title;

    private String version;

    /**
     * Return content type associated with this content
     *
     * @return Content type object
     */
    public ContentType getContentType()
    {
        return getRepository().getContentType(contentType);
    }

    /**
     * Getter method for createdTime
     *
     * @return Returns the createdTime.
     */
    public Date getCreatedTime()
    {
        return createdTime;
    }

    /**
     * Get long unique id
     *
     * @return Long unique id of this content
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter method for lastModified
     *
     * @return Returns the lastModified.
     */
    public Date getLastModified()
    {
        return lastModified;
    }

    /**
     * Getter method for propertyManager
     *
     * @return Returns the propertyManager.
     */
    public PropertyManager getPropertyManager()
    {
        return propertyManager;
    }

    /**
     * Getter method for repository
     *
     * @return Returns the repository.
     */
    public Repository getRepository()
    {
        return repository;
    }

    /**
     * Getter method for title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Getter method for version
     *
     * @return Returns the version.
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * Setter method for contentType
     *
     * @param contentType The contentType to set.
     */
    void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * Setter method for createdTime
     *
     * @param createdTime The createdTime to set.
     */
    void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    /**
     * Setter method for id
     *
     * @param id The id to set.
     */
    void setId(long id)
    {
        this.id = id;
    }

    /**
     * Setter method for lastModified
     *
     * @param lastModified The lastModified to set.
     */
    void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    /**
     * Setter method for repository
     *
     * @param repository The repository to set.
     */
    void setRepository(Repository repository)
    {
        this.repository = repository;
    }

    /**
     * Setter method for title
     *
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Setter method for version
     *
     * @param version The version to set.
     */
    void setVersion(String version)
    {
        this.version = version;
    }
}