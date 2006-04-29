/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.web;

import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column sorting status
 */
public final class ColumnSort
    extends Enum
{
    /**
     * Generated serial ID
     */
    private static final long serialVersionUID = 5183468517358245865L;

    /** Ascendingly sorted */
    public static ColumnSort ASC = new ColumnSort( "asc" );

    /** Descendingly sorted */
    public static ColumnSort DESC = new ColumnSort( "desc" );

    /** Sorting is disabled */
    public static ColumnSort DISABLED = new ColumnSort( "disabled" );

    /** Sortable but not sorted */
    public static ColumnSort UNSORTED = new ColumnSort( "unsorted" );

    /**
     * Value of a given string
     *
     * @param value String value
     * @return ColumnSort object
     */
    public static ColumnSort valueOf( String value )
    {
        ColumnSort sort = (ColumnSort) getEnum( ColumnSort.class, value );
        if ( sort == null )
        {
            throw new IllegalArgumentException( value + " is not a legal column sort option" );
        }
        return sort;
    }

    /**
     * Constructor for class ColumnSort
     *
     * @param name Name of enum
     */
    private ColumnSort( String name )
    {
        super( name );
    }

    /**
     * Overwrite or implement method toString()
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getName();
    }
}
