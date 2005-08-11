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
public interface TableData
{
    /**
     * Open iterator of rows
     *
     * @return Iterator of rows
     * @throws Exception Just throw it out
     */
    Iterator openIterator() throws Exception;
}
