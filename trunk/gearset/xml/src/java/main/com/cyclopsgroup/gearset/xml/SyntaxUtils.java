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
package com.cyclopsgroup.gearset.xml;

import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.lang.StringUtils;

/**
 * Utility class for xml tags
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public final class SyntaxUtils
{
    /**
     * Check whether the attribute value is empty
     * 
     * @param attributeName
     *                   Name of the attribute to check
     * @param attributeValue
     *                   Value of the attribute to check
     * @throws MissingAttributeException
     *                    If value is empty, throw it out
     */
    public static final void checkAttribute(String attributeName,
            String attributeValue) throws MissingAttributeException
    {
        if (StringUtils.isEmpty(attributeValue))
        {
            throw new MissingAttributeException(attributeName);
        }
    }
}