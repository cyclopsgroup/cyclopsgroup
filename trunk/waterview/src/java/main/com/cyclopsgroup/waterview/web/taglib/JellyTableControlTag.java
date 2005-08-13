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
import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyScriptTag;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.web.Table;
import com.cyclopsgroup.waterview.web.TableAware;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Jelly table control tag
 */
public class JellyTableControlTag extends BaseJellyScriptTag implements
        TableAware
{
    private String script;

    private Table table;

    /**
     * Overwrite or implement method createScript()
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyScriptTag#createScript(org.apache.commons.jelly.JellyContext, org.apache.avalon.framework.service.ServiceManager)
     */
    protected Script createScript(JellyContext context,
            ServiceManager serviceManager) throws Exception
    {
        requireAttribute("script");

        invokeBody(XMLOutput.createDummyXMLOutput());

        ModuleManager ui = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        Path path = ui.parsePath(getScript());
        JellyEngine je = (JellyEngine) serviceManager.lookup(JellyEngine.ROLE);
        invokeBody(XMLOutput.createDummyXMLOutput());

        if (table == null)
        {
            throw new JellyException("One table must be defined");
        }
        context.setVariable("table", table);
        return je.getScript(path.getPackage(), path.getPath());
    }

    /**
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * Overwrite or implement method handleTable()
     *
     * @see com.cyclopsgroup.waterview.web.TableAware#handleTable(com.cyclopsgroup.waterview.web.Table)
     */
    public void handleTable(Table table) throws Exception
    {
        this.table = table;
    }

    /**
     * @param script The script to set.
     */
    public void setScript(String script)
    {
        this.script = script;
    }
}
