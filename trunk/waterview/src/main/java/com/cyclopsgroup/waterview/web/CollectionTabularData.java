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
package com.cyclopsgroup.waterview.web;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.collections.comparators.ComparatorChain;

import com.cyclopsgroup.waterview.utils.BeanPropertyComparator;
import com.cyclopsgroup.waterview.utils.HashCodeComparator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Collection implemented table data
 */
public class CollectionTabularData
    implements TabularData
{
    private Collection collection;

    /**
     * Constructor for type CollectionTableData
     *
     * @param collection Collecton of data
     */
    public CollectionTabularData( Collection collection )
    {
        this.collection = collection;
    }

    /**
     * Overwrite or implement method in CollectionTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#getSize()
     */
    public int getSize()
    {
        return collection.size();
    }

    /**
     * Overwrite or implement method in CollectionTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#isCountable()
     */
    public boolean isCountable()
    {
        return true;
    }

    /**
     * Overwrite or implement method in CollectionTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#openIterator(com.cyclopsgroup.waterview.web.Table)
     */
    public Iterator openIterator( Table table )
        throws Exception
    {
        String[] sortedColumnNames = table.getSortedColumns();
        if ( sortedColumnNames.length == 0 )
        {
            return collection.iterator();
        }
        ComparatorChain chain = new ComparatorChain();
        for ( int i = 0; i < sortedColumnNames.length; i++ )
        {
            String columnName = sortedColumnNames[i];
            Column column = table.getColumn( columnName );
            if ( column.getSort() == ColumnSort.ASC )
            {
                chain.addComparator( new BeanPropertyComparator( column.getName() ) );
            }
            else if ( column.getSort() == ColumnSort.DESC )
            {
                chain.addComparator( new BeanPropertyComparator( column.getName() ), true );
            }
        }
        chain.addComparator( HashCodeComparator.INSTANCE );
        TreeSet set = new TreeSet( chain );
        set.addAll( collection );
        return set.iterator();
    }
}
