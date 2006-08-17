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
package com.cyclopsgroup.waterview.ui.action;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.web.Column;
import com.cyclopsgroup.waterview.web.ColumnSort;
import com.cyclopsgroup.waterview.web.Table;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Action to sort table
 */
public class SortTable
    implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RunData data, ActionContext context )
        throws Exception
    {
        String tableId = data.getParameters().getString( "table_id", null );
        if ( tableId == null )
        {
            throw new IllegalArgumentException( "table_id is missing" );
        }
        Table table = (Table) data.getSessionContext().get( tableId );
        if ( table == null )
        {
            redirectBack( data, context );
            return;
        }

        String columnName = data.getParameters().getString( "table_column_name" );
        Column column = table.getColumn( columnName );
        if ( column == null || column.getSort() == ColumnSort.disabled )
        {
            redirectBack( data, context );
            return;
        }

        if ( column.getSort() == ColumnSort.asc )
        {
            column.setSort( ColumnSort.desc );
        }
        else if ( column.getSort() == ColumnSort.desc )
        {
            column.setSort( ColumnSort.unsorted );
            table.unsortOn( columnName );
        }
        else if ( column.getSort() == ColumnSort.unsorted )
        {
            column.setSort( ColumnSort.asc );
            table.sortOn( columnName );
        }

        redirectBack( data, context );
    }

    private void redirectBack( RunData data, ActionContext context )
    {
        String url = data.getRefererUrl();
        if ( url.indexOf( "keep_form=true" ) == -1 )
        {
            url += url.indexOf( '?' ) == -1 ? '?' : '&';
            url += "keep_form=true";
        }
        context.setTargetUrl( url );
    }
}
