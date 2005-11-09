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
package com.cyclopsgroup.waterview.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Runtime table instance
 */
public class Table
{
    private Map columns = ListOrderedMap.decorate(new HashMap());

    private String id;

    private int pageIndex = 0;

    private int pageSize = -1;

    private Set sortedColumns = ListOrderedSet.decorate(new HashSet());

    /**
     * Constructor for class Table
     *
     * @param id Unique table id
     */
    public Table(String id)
    {
        this.id = id;
    }

    /**
     * Add column dynamically
     *
     * @param column Column to add
     */
    public void addColumn(Column column)
    {
        columns.put(column.getName(), column);
    }

    /**
     * Get column
     *
     * @param name Name of the column
     * @return Column object
     */
    public Column getColumn(String name)
    {
        return (Column) columns.get(name);
    }

    /**
     * Get column name array
     *
     * @return Array of column names
     */
    public String[] getColumnNames()
    {
        return (String[]) columns.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Get column array
     *
     * @return Array of all columns
     */
    public Column[] getColumns()
    {
        return (Column[]) columns.values().toArray(Column.EMPTY_ARRAY);
    }

    /**
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Get page count
     *
     * @param data Tabular data
     * @return Page count
     * @throws Exception Throw it out
     */
    public int getPageCount(TabularData data) throws Exception
    {
        if (!data.isCountable())
        {
            throw new IllegalStateException("Tabular data is not countable");
        }
        if (getPageSize() <= 0)
        {
            throw new IllegalStateException("Table is not pagenized");
        }
        int recordCount = data.getSize();
        int pageCount = recordCount / getPageSize();
        if (recordCount % getPageSize() > 0)
        {
            pageCount++;
        }
        return pageCount;
    }

    /**
     * Get page index
     *
     * @return Page index
     */
    public int getPageIndex()
    {
        return pageIndex;
    }

    /**
     * Getter method for pageSize
     *
     * @return Returns the pageSize.
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * Get sorted column names
     *
     * @return Array of column names
     */
    public String[] getSortedColumns()
    {
        return (String[]) sortedColumns.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Set page index
     *
     * @param pageIndex Page index
     */
    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    /**
     * Setter method for pageSize
     *
     * @param pageSize The pageSize to set.
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * Sort on column
     *
     * @param columnName Column name
     */
    public void sortOn(String columnName)
    {
        sortedColumns.add(columnName);
    }

    /**
     * Unsort on column
     *
     * @param columnName Name of column
     */
    public void unsortOn(String columnName)
    {
        sortedColumns.remove(columnName);
    }
}
