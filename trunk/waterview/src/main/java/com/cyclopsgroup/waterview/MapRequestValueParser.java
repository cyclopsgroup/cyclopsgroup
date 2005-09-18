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
package com.cyclopsgroup.waterview;

import java.util.Properties;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.ArrayUtils;

/**
 * HashMap based request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
class MapRequestValueParser
    extends RequestValueParser
{
    private Properties content = new Properties();

    /**
     * Override method doGetAttributeNames in class MapRequestValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#doGetAttributeNames()
     */
    protected String[] doGetAttributeNames()
    {
        return (String[]) content.keySet().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }

    /**
     * Overwrite or implement method add()
     * @see com.cyclopsgroup.waterview.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add( String name, String value )
    {
        content.setProperty( name, value );
    }

    /**
     * Overwrite or implement method doGetValue()
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue( String name )
        throws Exception
    {
        return content.getProperty( name );
    }

    /**
     * Overwrite or implement method doGetValues()
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues( String name )
        throws Exception
    {
        return new String[] { doGetValue( name ) };
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItem(java.lang.String)
     */
    public FileItem getFileItem( String name )
    {
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItems(java.lang.String)
     */
    public FileItem[] getFileItems( String name )
    {
        return EMPTY_FILEITEM_ARRAY;
    }

    /**
     * Overwrite or implement method remove()
     * @see com.cyclopsgroup.waterview.ValueParser#remove(java.lang.String)
     */
    public void remove( String name )
    {
        content.remove( name );
    }
}
