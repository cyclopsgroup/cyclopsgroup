/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Mark the data source to be scrollable
 */
public interface Scrollable
{
    /**
     * Current page index
     *
     * @return Current page index
     */
    int getPageIndex();

    /**
     * Page size
     *
     * @return Page size
     */
    int getPageSize();

    /**
     * Total pages
     *
     * @return Total pages
     */
    int getTotalPages();

    /**
     * Set current page index
     *
     * @param pageIndex Current page index
     */
    void setPageIndex(int pageIndex);
}
