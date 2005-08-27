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
package com.cyclopsgroup.tornado.jelly.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.tornado.components.form.FormManager;
import com.cyclopsgroup.tornado.core.form.Form;
import com.cyclopsgroup.tornado.core.form.FormDefinition;
import com.cyclopsgroup.waterview.jelly.AbstractTag;

public class FormTag extends AbstractTag
{
    private static final String FORM_ID = "onlyyou";

    /**
     * Overwrite or implement method doTag()
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireParent(FormContainer.class);
        String formName = "DynaForm_" + getBody().hashCode();
        FormManager fm = (FormManager) serviceManager.lookup(FormManager.ROLE);
        FormDefinition fd = fm.getNamedFormDefinition(formName);
        if (fd == null)
        {
            fd = new FormDefinition(formName);
            getContext().setVariable(FormDefinition.NAME, fd);
            invokeBody(output);
            getContext().setVariable(FormDefinition.NAME, null);
        }
        Form form = fm.getForm(fd, FORM_ID, getRuntime());
        ((FormContainer) getParent()).handleForm(form);
    }
}
