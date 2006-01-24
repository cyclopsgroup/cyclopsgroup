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
package com.cyclopsgroup.tornado.hibernate.taglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HqlParameterTag
    extends TagSupport
{
    private String name;

    private Object value;

    private String type;

    /**
     * Getter method for name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Getter method for type
     *
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Setter method for type
     *
     * @param type The type to set.
     */
    public void setType( String type )
    {
        this.type = type;
    }

    /**
     * Getter method for value
     *
     * @return Returns the value.
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Setter method for value
     *
     * @param value The value to set.
     */
    public void setValue( Object value )
    {
        this.value = value;
    }

    /**
     * Override method processTag in class HQLParameterTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        HqlTabularDataTag tabularDataTag = (HqlTabularDataTag) requireParent( HqlTabularDataTag.class );
        tabularDataTag.addParameter( this );
    }

}
