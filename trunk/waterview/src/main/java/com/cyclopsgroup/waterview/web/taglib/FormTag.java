/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.jelly.LocationAware;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.Tag;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.web.Form;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Form tag
 */
public class FormTag extends TagSupport implements LocationAware
{
    private String action;

    private Script bodyScript;

    private List submitTags = new ArrayList();

    private Map fieldTags = ListOrderedMap.decorate(new Hashtable());

    private Form form;

    private boolean formNew;

    private boolean manual = false;

    private String method = "get";

    public void addSubmitTag(Tag tag)
    {
        if (!submitTags.contains(tag))
        {
            submitTags.add(tag);
        }
    }

    public void addFieldTag(FieldTag tag)
    {
        fieldTags.put(tag.getName(), tag);
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("id");
        requireAttribute("method");
        requireParent(FormTagAware.class);
        String formId = "form/" + getTagId();
        RuntimeData data = (RuntimeData) context.getVariable(RuntimeData.NAME);
        form = (Form) data.getSessionContext().get(formId);
        formNew = false;
        if (form == null || data.getRequestParameters().getBoolean("keep_form"))
        {
            formNew = true;
            form = new Form(formId);
            data.getSessionContext().put(formId, form);
        }
        invokeBody(XMLOutput.createDummyXMLOutput());
        ((FormTagAware) getParent()).handleFormTag(this);
    }

    /**
     * Getter method for property action
     *
     * @return Returns the action.
     */
    public String getAction()
    {
        return action;
    }

    /**
     * Getter method for property bodyScript
     *
     * @return Returns the bodyScript.
     */
    public Script getBodyScript()
    {
        return bodyScript;
    }

    public List getSubmitTags()
    {
        return submitTags;
    }

    public FieldTag getFieldTag(String fieldName)
    {
        return (FieldTag) fieldTags.get(fieldName);
    }

    /**
     * @return Returns the form.path
     */
    public Form getForm()
    {
        return form;
    }

    /**
     * @return Returns the method.
     */
    public String getMethod()
    {
        return method;
    }

    public boolean isFormNew()
    {
        return formNew;
    }

    /**
     * Getter method for property manual
     *
     * @return Returns the manual.
     */
    public boolean isManual()
    {
        return manual;
    }

    /**
     * Setter method for property action
     *
     * @param action The action to set.
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * Setter method for property bodyScript
     *
     * @param bodyScript The bodyScript to set.
     */
    public void setBodyScript(Script bodyScript)
    {
        this.bodyScript = bodyScript;
    }

    /**
     * Setter method for property manual
     *
     * @param manual The manual to set.
     */
    public void setManual(boolean manual)
    {
        this.manual = manual;
    }

    /**
     * @param method The method to set.
     */
    public void setMethod(String method)
    {
        this.method = method;
    }
}
