/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Abstract implementation of scrollable table data
 */
public abstract class BaseScrollableTableData implements TableData, Scrollable
{
    private int pageIndex = 0;

    private int pageSize;

    private int totalPages;

    /**
     * Constructor for class BaseScrollableTableData
     *
     * @param total Total amount of row
     * @param pageSize Page size
     */
    protected BaseScrollableTableData(int total, int pageSize)
    {
        this.pageSize = pageSize;
        this.totalPages = total / pageSize;
        if (total % pageSize != 0)
        {
            totalPages++;
        }
    }

    /**
     * Overwrite or implement method getPageIndex()
     * @see com.cyclopsgroup.waterview.web.Scrollable#getPageIndex()
     */
    public int getPageIndex()
    {
        return pageIndex;
    }

    /**
     * Overwrite or implement method getPageSize()
     *
     * @see com.cyclopsgroup.waterview.web.Scrollable#getPageSize()
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * Overwrite or implement method getTotalPages()
     *
     * @see com.cyclopsgroup.waterview.web.Scrollable#getTotalPages()
     */
    public int getTotalPages()
    {
        return totalPages;
    }

    /**
     * Overwrite or implement method setPageIndex()
     *
     * @see com.cyclopsgroup.waterview.web.Scrollable#setPageIndex(int)
     */
    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }
}
