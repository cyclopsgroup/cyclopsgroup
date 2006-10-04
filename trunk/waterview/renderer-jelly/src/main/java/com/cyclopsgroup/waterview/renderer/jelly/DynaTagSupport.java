/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.renderer.jelly;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jelly.DynaTag;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Dynamic tag support
 */
public abstract class DynaTagSupport
    extends TagSupportBase
    implements DynaTag
{
    private final Map<String, Object> attributeMap = new HashMap<String, Object>();

    /**
     * Getter method for property attributeMap
     *
     * @return Returns the attributeMap.
     */
    public Map<String, Object> getAttributeMap()
    {
        return attributeMap;
    }

    /**
     * Overwrite or implement method getAttributeType()
     *
     * @see org.apache.commons.jelly.DynaTag#getAttributeType(java.lang.String)
     */
    public Class<Object> getAttributeType( String name )
        throws JellyTagException
    {
        return Object.class;
    }

    /**
     * Check attribute
     *
     * @param attributeName Attribute name
     * @throws MissingAttributeException Missing attribute
     */
    protected void requireAttribute( String attributeName )
        throws MissingAttributeException
    {
        if ( !attributeMap.containsKey( attributeName ) )
        {
            throw new MissingAttributeException( attributeName );
        }
    }

    /**
     * Overwrite or implement method setAttribute()
     *
     * @see org.apache.commons.jelly.DynaTag#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute( String name, Object value )
        throws JellyTagException
    {
        attributeMap.put( name, value );
    }
}
