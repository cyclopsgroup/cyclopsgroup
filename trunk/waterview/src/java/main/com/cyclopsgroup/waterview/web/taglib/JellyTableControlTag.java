/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.web.TabularData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Jelly table control tag
 */
public class JellyTableControlTag extends BaseTag implements TableControlTag
{
    private String script;

    private TableTag tableTag;

    private TabularData data;

    /**
     * Get data of this table
     *
     * @return TabularData
     */
    public TabularData getData()
    {
        return data;
    }

    /**
     * Setter method for property data
     *
     * @param data Tabular data to set
     */
    public void setData(TabularData data)
    {
        this.data = data;
    }

    /**
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * @param script The script to set.
     */
    public void setScript(String script)
    {
        this.script = script;
    }

    /**
     * Overwrite or implement method in JellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.web.taglib.TableControlTag#setTableTag(com.cyclopsgroup.waterview.web.taglib.TableTag)
     */
    public void setTableTag(TableTag tableTag)
    {
        this.tableTag = tableTag;
    }

    /**
     * Overwrite or implement method in JellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.web.taglib.TableControlTag#setTabularData(com.cyclopsgroup.waterview.web.TabularData)
     */
    public void setTabularData(TabularData tabularData)
    {
        setData(tabularData);
    }

    /**
     * Overwrite or implement method in JellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("script");

        invokeBody(XMLOutput.createDummyXMLOutput());

        ModuleManager ui = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        Path path = ui.parsePath(getScript());
        JellyEngine je = (JellyEngine) serviceManager.lookup(JellyEngine.ROLE);
        Script s = je.getScript(path.getPackage(), path.getPath());

        if (tableTag == null)
        {
            throw new JellyException("One table must be defined");
        }

        if (data == null)
        {
            throw new JellyException("Tabular data must be included");
        }
        
        JellyContext jc = new JellyContext(getContext());
        jc.setVariable("tableTag", tableTag);
        jc.setVariable("table", tableTag.getTable());
        jc.setVariable("tabularData", getData());
        s.run(jc, output);
    }
}
