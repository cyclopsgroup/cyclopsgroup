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

import java.util.HashMap;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.LargeList;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.web.Table;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag for table
 */
public class TableTag
    extends TagSupport
{
    private Table table;

    private String var;

    private boolean tableNew = false;

    private HashMap columnTags;

    private int pageSize = -1;

    private LargeList tabularData;

    /**
     * Getter method for field data
     *
     * @return Returns the data.
     */
    public LargeList getData()
    {
        return tabularData;
    }

    /**
     * Setter method for field data
     *
     * @param data The data to set.
     */
    public void setData( LargeList data )
    {
        this.tabularData = data;
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
     * Setter method for pageSize
     *
     * @param pageSize The pageSize to set.
     */
    public void setPageSize( int pageSize )
    {
        this.pageSize = pageSize;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "var" );
        requireAttribute( "name" );
        requireParent( TableControlTag.class );
        String tableId = "table_" + getUniqueTagId();
        RunData data = getRuntimeData();
        table = (Table) data.getSessionContext().get( tableId );
        tableNew = table == null;
        if ( tableNew )
        {
            table = new Table( tableId );
            table.setPageSize( getPageSize() );
            data.getSessionContext().put( tableId, table );
        }
        columnTags = new HashMap();
        invokeBody( output );
        ( (TableControlTag) getParent() ).setTableTag( this );
        if ( getData() != null )
        {
            ( (TableControlTag) getParent() ).setTabularData( getData() );
        }
    }

    /**
     * Add column tag
     *
     * @param columnTag Column tag
     */
    public void addColumnTag( ColumnTag columnTag )
    {
        columnTags.put( columnTag.getName(), columnTag );
        if ( tableNew )
        {
            getTable().addColumn( columnTag.getColumn() );
        }
    }

    /**
     * Get column tag
     *
     * @param columnName Name of the column
     * @return ColumnTag object
     */
    public ColumnTag getColumnTag( String columnName )
    {
        return (ColumnTag) columnTags.get( columnName );
    }

    /**
     * Is the table new created
     *
     * @return True if table is new
     */
    public boolean isTableNew()
    {
        return tableNew;
    }

    /**
     * Getter method for field name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return getTagId();
    }

    /**
     * @return Returns the table.
     */
    public Table getTable()
    {
        return table;
    }

    /**
     * @return Returns the var.
     */
    public String getVar()
    {
        return var;
    }

    /**
     * Setter method for field name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        setTagId( name );
    }

    /**
     * @param var The var to set.
     */
    public void setVar( String var )
    {
        this.var = var;
    }
}