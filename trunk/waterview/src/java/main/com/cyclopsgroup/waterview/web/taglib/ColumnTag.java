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

import java.io.StringWriter;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.web.Column;
import com.cyclopsgroup.waterview.web.ColumnDisplayPolicy;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column tag
 */
public class ColumnTag extends BaseTag implements Column
{
    private Script bodyScript;

    private String display = ColumnDisplayPolicy.OPTIONAL.getName();

    private Script headerScript;

    private String name;

    private boolean sortable;

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

        invokeBody(output);

        ((TableTag) getParent()).getTable().addColumn(this);
    }

    /**
     * Overwrite or implement method getBody()
     *
     * @see com.cyclopsgroup.waterview.web.Column#getBody(java.lang.Object)
     */
    public String getBody(Object row) throws Exception
    {
        String rowName = ((TableTag) getParent()).getVar();
        JellyContext jc = new JellyContext(getContext());
        jc.setVariable(rowName, row);
        return runScript(getBodyScript(), jc);
    }

    /**
     * @return Returns the bodyScript.
     */
    public Script getBodyScript()
    {
        return bodyScript;
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
     * Overwrite or implement method getDisplayPolicy()
     *
     * @see com.cyclopsgroup.waterview.web.Column#getDisplayPolicy()
     */
    public ColumnDisplayPolicy getDisplayPolicy()
    {
        ColumnDisplayPolicy policy = ColumnDisplayPolicy.valueOf(getDisplay());
        if (policy == null)
        {
            policy = ColumnDisplayPolicy.OPTIONAL;
        }
        return policy;
    }

    /**
     * Overwrite or implement method getHeader()
     *
     * @see com.cyclopsgroup.waterview.web.Column#getHeader()
     */
    public String getHeader() throws JellyTagException
    {
        return runScript(getHeaderScript(), getContext());
    }

    /**
     * @return Returns the titleScript.
     */
    public Script getHeaderScript()
    {
        return headerScript;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Returns the sortable.
     */
    public boolean isSortable()
    {
        return sortable;
    }

    private String runScript(Script script, JellyContext ctx)
            throws JellyTagException
    {
        if (script == null)
        {
            return StringUtils.EMPTY;
        }
        StringWriter sw = new StringWriter();
        script.run(ctx, XMLOutput.createXMLOutput(sw));
        return sw.toString();
    }

    /**
     * @param bodyScript The bodyScript to set.
     */
    public void setBodyScript(Script bodyScript)
    {
        this.bodyScript = bodyScript;
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
     * @param titleScript The titleScript to set.
     */
    public void setHeaderScript(Script titleScript)
    {
        this.headerScript = titleScript;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param sortable The sortable to set.
     */
    public void setSortable(boolean sortable)
    {
        this.sortable = sortable;
    }
}
