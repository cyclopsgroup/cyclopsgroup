/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.ui.action;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.web.Table;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Scroll the table
 */
public class ScrollTable implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute(RuntimeData data, ActionContext context)
            throws Exception
    {
        String tableId = data.getParams()
                .getString("table_id", null);
        if (tableId == null)
        {
            throw new IllegalArgumentException("table_id is missing");
        }
        Table table = (Table) data.getSessionContext().get(tableId);
        if (table != null)
        {
            int pageIndex = data.getParams()
                    .getInt("page_index", -1);
            if (pageIndex != -1)
            {
                table.setPageIndex(pageIndex);
            }
        }
    }
}
