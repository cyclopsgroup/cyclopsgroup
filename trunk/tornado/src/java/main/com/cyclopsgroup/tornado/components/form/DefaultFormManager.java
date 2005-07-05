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
package com.cyclopsgroup.tornado.components.form;

import com.cyclopsgroup.tornado.core.form.Form;
import com.cyclopsgroup.tornado.core.form.FormDefinition;
import com.cyclopsgroup.waterview.PageRuntime;

public class DefaultFormManager implements FormManager
{
    public Form createForm(FormDefinition definition, String formId,
            PageRuntime runtime)
    {
        Form form = new Form(definition, formId);
        String key = createFormKey(definition, formId);
        runtime.getSessionContext().put(key, form);
        return form;
    }

    private String createFormKey(FormDefinition definition, String formId)
    {
        return "cyclopsgroup/form/" + definition.getName() + "/" + formId;

    }

    /**
     * Overwrite or implement method getForm()
     * @see com.cyclopsgroup.tornado.components.form.FormManager#getForm(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public Form findForm(FormDefinition definition, String formId,
            PageRuntime runtime)
    {
        String key = createFormKey(definition, formId);
        return (Form) runtime.getSessionContext().get(key);
    }

    /**
     * Overwrite or implement method getForm()
     * @see com.cyclopsgroup.tornado.components.form.FormManager#getForm(com.cyclopsgroup.tornado.core.form.FormDefinition, java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public Form getForm(FormDefinition definition, String formId,
            PageRuntime runtime)
    {
        Form form = findForm(definition, formId, runtime);
        if (form == null)
        {
            form = createForm(definition, formId, runtime);
        }
        return form;
    }

    /**
     * Overwrite or implement method getFormDefinition()
     * @see com.cyclopsgroup.tornado.components.form.FormManager#getFormDefinition(java.lang.String)
     */
    public FormDefinition getFormDefinition(String formName)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
