/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Collection implemented table data
 */
public class CollectionTableData extends BaseScrollableTableData
{
    private Collection collection;

    /**
     * Constructor for type CollectionTableData
     *
     * @param collection Collecton of data
     * @param pageSize Size of a page
     */
    public CollectionTableData(Collection collection, int pageSize)
    {
        super(collection.size(), pageSize);
        this.collection = collection;
    }

    /**
     * Overwrite or implement method openIterator()
     *
     * @see com.cyclopsgroup.waterview.web.TableData#openIterator()
     */
    public Iterator openIterator()
    {
        return collection.iterator();
    }
}
