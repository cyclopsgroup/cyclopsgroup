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
package com.cyclopsgroup.cyclib.jelly;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.Tag;
import org.apache.commons.lang.StringUtils;

/**
 * Utilities for jelly tags
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class TagUtils
{
    /**
     * Make sure an attribute is available
     *
     * @param attributeName Name of the attribute
     * @param attributeValue Value of the attribute
     * @throws MissingAttributeException Throw it out if attribute value is not available
     */
    public static void requireAttribute(String attributeName,
            String attributeValue) throws MissingAttributeException
    {
        if (StringUtils.isEmpty(attributeValue))
        {
            throw new MissingAttributeException(attributeName);
        }
    }

    /**
     * Make sure parent tag is an instanceof given class
     *
     * @param currentTag Current tag object
     * @param parentTagClass Class of parent tag
     * @throws JellyTagException Throw it out if class doesn't match
     */
    public static void requireParent(Tag currentTag, Class parentTagClass)
            throws JellyTagException
    {
        if (!parentTagClass.isAssignableFrom(currentTag.getParent().getClass()))
        {
            throw new JellyTagException("Parent tag of [" + currentTag
                    + "] must be instance of " + parentTagClass.getName());
        }
    }
}