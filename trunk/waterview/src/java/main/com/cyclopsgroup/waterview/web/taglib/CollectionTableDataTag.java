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

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.collections.iterators.EnumerationIterator;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.web.TableData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Table data tag accepting a collection, iterator, enumeration or array
 */
public class CollectionTableDataTag extends BaseTag implements TableData
{
    private Object items;

    /**
     * Overwrite or implement method doTag()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("items");
        requireParent(TableTag.class);

        ((TableTag) getParent()).setData(this);
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
     * Overwrite or implement method openIterator()
     *
     * @see com.cyclopsgroup.waterview.web.TableData#openIterator()
     */
    public Iterator openIterator() throws Exception
    {
        if (getItems() instanceof Collection)
        {
            return ((Collection) getItems()).iterator();
        }
        else if (getItems() instanceof Iterator)
        {
            return (Iterator) getItems();
        }
        else if (getItems() instanceof Enumeration)
        {
            return new EnumerationIterator((Enumeration) getItems());
        }
        else if (getItems() instanceof Object[])
        {
            return new ArrayIterator((Object[]) getItems());
        }
        else
        {
            return Collections.EMPTY_LIST.iterator();
        }
    }

    /**
     * Setter method for field items
     *
     * @param items The items to set.
     */
    public void setItems(Object items)
    {
        this.items = items;
    }

}
