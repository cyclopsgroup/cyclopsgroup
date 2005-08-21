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

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.util.TypeUtils;
import com.cyclopsgroup.waterview.web.Column;
import com.cyclopsgroup.waterview.web.ColumnDisplay;
import com.cyclopsgroup.waterview.web.ColumnSort;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column tag
 */
public class ColumnTag extends BaseTag
{
    private String display = ColumnDisplay.OPTIONAL.getName();

    private String sort = ColumnSort.DISABLED.getName();

    private String name;

    private String type = "string";

    private String value = StringUtils.EMPTY;

    private String title = StringUtils.EMPTY;

    private Column column;

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
     * Overwrite or implement method doTag()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
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
        }
        ((TableTag) getParent()).addColumnTag(this);
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
     * Get sort option
     *
     * @return Sort option
     */
    public String getSort()
    {
        return sort;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
