/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

import java.util.Iterator;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Table data 
 */
public interface TabularData
{
    /**
     * Open iterator of rows
     *
     * @param table Table to show this data
     * @return Iterator of rows
     * @throws Exception Just throw it out
     */
    Iterator openIterator( Table table )
        throws Exception;

    /**
     * If the data is countable
     * 
     * @return True if it's countable
     */
    boolean isCountable();

    /**
     * Get size of the whole data
     *
     * @return Size of data or -1 if data is not countable
     */
    int getSize();
}
