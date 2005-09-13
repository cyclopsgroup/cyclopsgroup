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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.web.Form;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Form tag
 */
public class FormTag extends TagSupport
{
    private String action;

    private Script bodyScript;

    private List buttonTags;

    private Map fieldTags;

    private Form form;

    private boolean formNew;

    private boolean hideControls;

    private String method = "get";

    /**
     * Add submit tag
     *
     * @param tag Submit tag
     */
    public void addButtonTag(SubmitTag tag)
    {
        if (!buttonTags.contains(tag))
        {
            buttonTags.add(tag);
        }
    }

    /**
     * Add field tag
     *
     * @param tag Field tag
     */
    public void addFieldTag(FieldTag tag)
    {
        fieldTags.put(tag.getName(), tag);
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

    /**
     * Get submit tags
     *
     * @return Submit tags
     */
    public List getButtonTags()
    {
        return buttonTags;
    }

    /**
     * Get field tag
     *
     * @param fieldName Field name
     * @return Field tag object or null
     */
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

    /**
     * Set name
     *
     * @return Name to set
     */
    public String getName()
    {
        return getTagId();
    }

    /**
     * If the for is new created
     *
     * @return True if form is new
     */
    public boolean isFormNew()
    {
        return formNew;
    }

    /**
     * Getter method for property hideControls
     *
     * @return Returns the hideControls.
     */
    public boolean isHideControls()
    {
        return hideControls;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("name");
        requireAttribute("method");
        requireParent(FormControlTag.class);
        fieldTags = ListOrderedMap.decorate(new Hashtable());
        buttonTags = new ArrayList();
        String formId = "form/" + getUniqueTagId();
        RuntimeData data = (RuntimeData) context.getVariable(RuntimeData.NAME);
        form = (Form) data.getSessionContext().get(formId);
        formNew = false;
        if (form == null || !data.getParams().getBoolean("keep_form"))
        {
            formNew = true;
            form = new Form(formId);
            data.getSessionContext().put(formId, form);
        }
        invokeBody(XMLOutput.createDummyXMLOutput());
        formNew = false;
        ((FormControlTag) getParent()).setFormTag(this);
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
     * Setter method for property hideControls
     *
     * @param hideControls The hideControls to set.
     */
    public void setHideControls(boolean hideControls)
    {
        this.hideControls = hideControls;
    }

    /**
     * @param method The method to set.
     */
    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * Set name
     *
     * @param name Name
     */
    public void setName(String name)
    {
        setTagId(name);
    }
}
