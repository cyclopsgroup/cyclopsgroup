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
package com.cyclopsgroup.waterview.utils;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Comparator based on a property
 */
public class BeanPropertyComparator
    implements Comparator
{
    private String propertyName;

    /**
     * Constructor for class BeanPropertyComparator
     *
     * @param propertyName Property name
     */
    public BeanPropertyComparator( String propertyName )
    {
        this.propertyName = propertyName;
    }

    /**
     * Overwrite or implement method compare()
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare( Object o1, Object o2 )
    {
        try
        {
            Comparable p1 = (Comparable) PropertyUtils.getProperty( o1, propertyName );
            Comparable p2 = (Comparable) PropertyUtils.getProperty( o2, propertyName );
            return p1.compareTo( p2 );
        }
        catch ( Exception e )
        {
            return 0;
        }
    }
}
