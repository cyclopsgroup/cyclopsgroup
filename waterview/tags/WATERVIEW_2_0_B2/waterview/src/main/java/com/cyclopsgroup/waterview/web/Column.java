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
package com.cyclopsgroup.waterview.web;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column model
 */
public class Column
{
    /** Empty column array */
    public static final Column[] EMPTY_ARRAY = new Column[0];

    private ColumnDisplay display = ColumnDisplay.OPTIONAL;

    private String name;

    private ColumnSort sort = ColumnSort.DISABLED;

    private Class type;

    private String value;

    /**
     * Constructor for class Column
     *
     * @param name
     * @param type
     */
    public Column( String name, Class type )
    {
        this.name = name;
        this.type = type;
    }

    /**
     * Get display option of this column
     *
     * @return Dispaly option of column
     */
    public ColumnDisplay getDisplay()
    {
        return display;
    }

    /**
     * Get column name
     *
     * @return Name of the column
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get sort option
     *
     * @return Sort option
     */
    public ColumnSort getSort()
    {
        return sort;
    }

    /**
     * Get column type
     *
     * @return Type of column
     */
    public Class getType()
    {
        return type;
    }

    /**
     * Get value
     *
     * @return String value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Set display option of column
     *
     * @param display Dispaly option to set
     */
    public void setDisplay( ColumnDisplay display )
    {
        this.display = display;
    }

    /**
     * Set sort option
     *
     * @param sort Sort option
     */
    public void setSort( ColumnSort sort )
    {
        this.sort = sort;
    }

    /**
     * Set value
     *
     * @param value Value to set
     */
    public void setValue( String value )
    {
        this.value = value;
    }
}
