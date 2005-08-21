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
    
    private String name;
    
    private String value;
    
    private ColumnDisplay display = ColumnDisplay.OPTIONAL;
    
    private ColumnSort sort = ColumnSort.DISABLED;
    
    private Class type;

    /**
     * Constructor for class Column
     *
     * @param name
     * @param type
     */
    public Column(String name, Class type)
    {
        this.name = name;
        this.type = type;
    }

    public ColumnDisplay getDisplay()
    {
        return display;
    }

    public void setDisplay(ColumnDisplay display)
    {
        this.display = display;
    }

    public ColumnSort getSort()
    {
        return sort;
    }

    public void setSort(ColumnSort sort)
    {
        this.sort = sort;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public Class getType()
    {
        return type;
    }
}
