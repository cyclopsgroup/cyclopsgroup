/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Show a form button
 */
public class FormButtonTag extends TagSupport
{
    private String description;

    private String title;

    /**
     * Getter method for field description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for field title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireParent(SubmitTag.class);
        if (StringUtils.isEmpty(getTitle()))
        {
            setTitle(getBodyText());
        }
        JellyEngine je = (JellyEngine) getServiceManager().lookup(
                JellyEngine.ROLE);
        Script script = je.getScript("/waterview/FormButton.jelly");
        getContext().setVariable("button", this);
        script.run(getContext(), output);
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Setter method for field title
     *
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
