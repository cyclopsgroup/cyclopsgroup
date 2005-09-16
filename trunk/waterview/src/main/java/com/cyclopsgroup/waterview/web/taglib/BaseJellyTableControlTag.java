/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag;
import com.cyclopsgroup.waterview.web.TabularData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Jelly table control tag
 */
public abstract class BaseJellyTableControlTag
    extends BaseJellyControlTag
    implements TableControlTag
{
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
    public void setData( TabularData data )
    {
        this.data = data;
    }

    /**
     * Overwrite or implement method in JellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.web.taglib.TableControlTag#setTableTag(com.cyclopsgroup.waterview.web.taglib.TableTag)
     */
    public void setTableTag( TableTag tableTag )
    {
        this.tableTag = tableTag;
    }

    /**
     * Overwrite or implement method in JellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.web.taglib.TableControlTag#setTabularData(com.cyclopsgroup.waterview.web.TabularData)
     */
    public void setTabularData( TabularData tabularData )
    {
        setData( tabularData );
    }

    /**
     * Override method runScript in class BaseJellyTableControlTag
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag#runScript(org.apache.commons.jelly.Script, org.apache.commons.jelly.XMLOutput)
     */
    protected void runScript( Script script, XMLOutput output )
        throws Exception
    {
        invokeBody( XMLOutput.createDummyXMLOutput() );
        if ( tableTag == null )
        {
            throw new JellyException( "One table must be defined" );
        }

        if ( data == null )
        {
            throw new JellyException( "Tabular data must be included" );
        }

        JellyContext jc = new JellyContext( getContext() );
        jc.setVariable( "tableTag", tableTag );
        jc.setVariable( "table", tableTag.getTable() );
        jc.setVariable( "tabularData", getData() );
        script.run( jc, output );
    }
}
