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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

/**
 * HashMap based request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MapParameters
    extends Parameters
{
    public static final List<FileItem> EMPTY_FILEITEM_LIST = Collections.unmodifiableList( new ArrayList<FileItem>() );

    private final Map<String, String> content = new HashMap<String, String>();

    @Override
    public void doAddAttribute( String name, String value )
    {
        content.put( name, value );
    }

    @Override
    protected Set<String> doGetAttributeNames()
    {
        return content.keySet();
    }

    @Override
    protected String doGetValue( String name )
        throws Exception
    {
        return content.get( name );
    }

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

    @Override
    public void doRemoveAttribute( String name )
    {
        content.remove( name );
    }

    @Override
    public FileItem getFileItem( String name )
    {
        return null;
    }

    @Override
    public List<FileItem> getFileItems( String name )
    {
        return EMPTY_FILEITEM_LIST;
    }
}
