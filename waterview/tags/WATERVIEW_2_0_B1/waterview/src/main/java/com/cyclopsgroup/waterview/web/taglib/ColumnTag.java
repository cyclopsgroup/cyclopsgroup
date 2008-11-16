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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.utils.TypeUtils;
import com.cyclopsgroup.waterview.web.Column;
import com.cyclopsgroup.waterview.web.ColumnDisplay;
import com.cyclopsgroup.waterview.web.ColumnSort;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column tag
 */
public class ColumnTag extends TagSupport
{
    private Column column;

    private String display = ColumnDisplay.OPTIONAL.getName();

    private String name;

    private String sort = ColumnSort.DISABLED.getName();

    private String title = StringUtils.EMPTY;

    private String type = "string";

    private String value = StringUtils.EMPTY;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("name");
        requireAttribute("display");
        requireParent(TableTag.class);
        if (((TableTag) getParent()).isTableNew())
        {
            Class columnType = TypeUtils.getType(getType());
            column = new Column(getName(), columnType);
            column.setDisplay(ColumnDisplay.valueOf(getDisplay()));
            column.setSort(ColumnSort.valueOf(getSort()));
            column.setValue(getValue());
            ((TableTag) getParent()).getTable().addColumn(column);
        }
        ((TableTag) getParent()).addColumnTag(this);
    }

    /**
     * Get column object
     *
     * @return Column object
     */
    public Column getColumn()
    {
        return column;
    }

    /**
     * Getter method for field display
     *
     * @return Returns the display.
     */
    public String getDisplay()
    {
        return display;
    }

    /**
     * @return Returns the name.
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
    public String getSort()
    {
        return sort;
    }

    /**
     * Get title of the column
     *
     * @return Title of the column
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * TODO Add java doc
     *
     * @return Type of column
     */
    public String getType()
    {
        return type;
    }

    /**
     * Get value of the column
     *
     * @return Value of the column
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Setter method for field display
     *
     * @param display The display to set.
     */
    public void setDisplay(String display)
    {
        this.display = display;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Set sort option
     *
     * @param sort Sort option to set
     */
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    /**
     * Set title of column
     *
     * @param title Title of the column
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * TODO Add java doc
     *
     * @param type Type of the column
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Set value of the column
     *
     * @param value Value of the column
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
