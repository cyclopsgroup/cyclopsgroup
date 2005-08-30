/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.tornado.utils;

import java.util.Comparator;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Comparator for class object
 */
public class ClassComparator implements Comparator
{
    /**
     * Overwrite or implement method compare()
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2)
    {
        Class c1 = (Class) o1;
        Class c2 = (Class) o2;
        return c1.getName().compareTo(c2.getName());
    }
}
