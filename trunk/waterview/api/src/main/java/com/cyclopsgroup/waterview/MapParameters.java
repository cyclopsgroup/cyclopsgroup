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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

/**
 * HashMap based request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
class MapParameters
    extends Parameters
{
    private HashMap<String, String> content = new HashMap<String, String>();

    /**
     * Overwrite or implement method add()
     * @see com.cyclopsgroup.waterview.Attributes#add(java.lang.String, java.lang.String)
     */
    @Override
    public void add( String name, String value )
    {
        content.put( name, value );
    }

    /**
     * Override method doGetAttributeNames in class MapRequestValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetAttributeNames()
     */
    @Override
    protected Set<String> doGetAttributeNames()
    {
        return content.keySet();
    }

    /**
     * Overwrite or implement method doGetValue()
     * @see com.cyclopsgroup.waterview.Attributes#doGetValue(java.lang.String)
     */
    @Override
    protected String doGetValue( String name )
        throws Exception
    {
        return content.get( name );
    }

    /**
     * Overwrite or implement method doGetValues()
     * @see com.cyclopsgroup.waterview.Attributes#doGetValues(java.lang.String)
     */
    @Override
    protected List<String> doGetValues( String name )
        throws Exception
    {
        String s = doGetValue( name );
        List<String> ret = new ArrayList<String>( 1 );
        if ( s != null )
        {
            ret.add( s );
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItem(java.lang.String)
     */
    @Override
    public FileItem getFileItem( String name )
    {
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItems(java.lang.String)
     */
    @Override
    public Collection<FileItem> getFileItems( String name )
    {
        return Collections.EMPTY_SET;
    }

    /**
     * Overwrite or implement method remove()
     * @see com.cyclopsgroup.waterview.Attributes#remove(java.lang.String)
     */
    @Override
    public void remove( String name )
    {
        content.remove( name );
    }
}
