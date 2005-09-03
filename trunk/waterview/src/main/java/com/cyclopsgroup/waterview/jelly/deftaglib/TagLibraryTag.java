/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Tag for tag library
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TagLibraryTag extends TagSupport
{
    private String uri;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    public void processTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        requireAttribute("uri");
        invokeBody(output);
    }

    /**
     * Getter method for uri
     *
     * @return Returns the uri.
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * Setter method for uri
     *
     * @param uri The uri to set.
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
