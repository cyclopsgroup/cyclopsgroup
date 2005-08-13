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

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.LocationAware;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.web.Table;
import com.cyclopsgroup.waterview.web.TableAware;
import com.cyclopsgroup.waterview.web.TableData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Tag for table
 */
public class TableTag extends BaseTag implements LocationAware
{
    private TableData data;

    private Table table;

    private String var;

    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("var");
        requireAttribute("name");
        requireParent(TableAware.class);
        String tableId = "wt_" + getUniqueTagId();
        RuntimeData data = getRuntimeData();
        table = (Table) data.getSessionContext().get(tableId);
        if (table == null)
        {
            table = new Table(tableId);
            invokeBody(output);
            if (getData() != null)
            {
                table.setData(getData());
            }
            data.getSessionContext().put(tableId, table);
        }
        ((TableAware) getParent()).handleTable(table);
    }

    /**
     * @return Returns the data.
     */
    public TableData getData()
    {
        return data;
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
     * @param data The data to set.
     */
    public void setData(TableData data)
    {
        this.data = data;
    }

    /**
     * Setter method for field name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        setTagId(name);
    }

    /**
     * @param table The table to set.
     */
    public void setTable(Table table)
    {
        this.table = table;
    }

    /**
     * @param var The var to set.
     */
    public void setVar(String var)
    {
        this.var = var;
    }
}