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
package com.cyclopsgroup.jlone.spi;

import java.util.HashMap;

import com.cyclopsgroup.jlone.Container;
import com.cyclopsgroup.jlone.Content;
import com.cyclopsgroup.jlone.ContentManager;
import com.cyclopsgroup.jlone.persistence.DataDocument;
import com.cyclopsgroup.jlone.persistence.PersistenceManager;
import com.cyclopsgroup.jlone.type.ContentType;

/**
 * Abstract content manager implementation
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public class AbstractContentManager implements ContentManager
{

    private HashMap contentTypes = new HashMap();

    private PersistenceManager persistenceManager;

    private Container rootContainer;

    /**
     * Add a content type
     * 
     * @param contentType
     *                   Content type object
     */
    public synchronized void addContentType(ContentType contentType)
    {
        contentTypes.put(contentType.getName(), contentType);
    }

    /**
     * Override method getContent in super class of AbstractContentManager
     * 
     * @see com.cyclopsgroup.jlone.ContentManager#getContent(long)
     */
    public Content getContent(long id)
    {
        DataDocument document = getPersistenceManager().load(id);
        ContentType type = getType(document.getTypeName());
        return type.getFactory().load(document);
    }

    /**
     * Override method getContent in super class of AbstractContentManager
     * 
     * @see com.cyclopsgroup.jlone.ContentManager#getContent(java.lang.String)
     */
    public Content getContent(String contentPath)
    {
        DataDocument document = getPersistenceManager().load(contentPath);
        ContentType type = getType(document.getTypeName());
        return type.getFactory().load(document);
    }

    /**
     * Get persistence manager instance
     * 
     * @return Instance of persistence manager
     */
    public PersistenceManager getPersistenceManager()
    {
        return persistenceManager;
    }

    /**
     * Override method getRootContainer in super class of AbstractContentManager
     * 
     * @see com.cyclopsgroup.jlone.ContentManager#getRootContainer()
     */
    public Container getRootContainer()
    {
        return rootContainer;
    }

    /**
     * Override method getType in super class of AbstractContentManager
     * 
     * @see com.cyclopsgroup.jlone.ContentManager#getType(java.lang.String)
     */
    public ContentType getType(String typeName)
    {
        return (ContentType) contentTypes.get(typeName);
    }
}