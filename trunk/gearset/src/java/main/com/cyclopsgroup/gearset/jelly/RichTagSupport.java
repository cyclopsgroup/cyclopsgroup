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
package com.cyclopsgroup.gearset.jelly;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MapTagSupport;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.gearset.bean.LogEnabled;
import com.cyclopsgroup.gearset.bean.MapValueParser;

/**
 * Rich tag support
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class RichTagSupport extends MapTagSupport implements
        LogEnabled
{
    private MapValueParser attributeParser;

    private Log logger;

    /**
     * If specified attribute is empty, throw an exception
     * 
     * @param attributeName Name of attribute
     * @throws MissingAttributeException Throw it if attribute is empty
     */
    protected void checkAttribute(String attributeName)
            throws MissingAttributeException
    {
        SyntaxUtils.checkAttribute(attributeName, getAttributeParser()
                .getString(attributeName));
    }

    /**
     * Check is parent tag is instance of specified class
     * 
     * @param clazz Specified tag class
     * @throws JellyTagException Throw it out if mismatch
     */
    protected void checkParent(Class clazz) throws JellyTagException
    {
        SyntaxUtils.checkParent(this, clazz);
    }

    /**
     * Override method doTag in super class of RichTagSupport
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public abstract void doTag(XMLOutput output)
            throws MissingAttributeException, JellyTagException;

    /**
     * Getter method for property attributeParser
     * 
     * @return Returns the attributeParser.
     */
    public MapValueParser getAttributeParser()
    {
        synchronized (this)
        {
            if (attributeParser == null)
            {
                attributeParser = new MapValueParser(getAttributes());
            }
        }
        return attributeParser;
    }

    /**
     * Override method getLogger in super class of RichTagSupport
     * 
     * @see com.cyclopsgroup.gearset.bean.LogEnabled#getLogger()
     */
    public Log getLogger()
    {
        synchronized (this)
        {
            if (logger == null)
            {
                logger = LogFactory.getLog(getClass());
            }
        }
        return logger;
    }
}