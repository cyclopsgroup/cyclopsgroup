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
package com.cyclopsgroup.waterview.web.taglib;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.utils.TypeUtils;
import com.cyclopsgroup.waterview.web.CollectionTabularData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Table data tag accepting a collection, iterator, enumeration or array
 */
public class CollectionTabularDataTag
    extends TagSupport
{
    private Object items;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "items" );
        requireParent( TableControlTag.class );

        List list = new ArrayList();
        CollectionUtils.addAll( list, TypeUtils.iterate( getItems() ) );
        ( (TableControlTag) getParent() ).setTabularData( new CollectionTabularData( list ) );
    }

    /**
     * Getter method for field items
     *
     * @return Returns the items.
     */
    public Object getItems()
    {
        return items;
    }

    /**
     * Setter method for field items
     *
     * @param items The items to set.
     */
    public void setItems( Object items )
    {
        this.items = items;
    }
}
