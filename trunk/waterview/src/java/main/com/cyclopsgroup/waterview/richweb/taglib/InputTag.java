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
package com.cyclopsgroup.waterview.richweb.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.AbstractTag;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class InputTag extends AbstractTag
{
    private boolean password = false;

    private boolean required = false;

    private String type = "string";

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        // TODO Auto-generated method stub

    }

    /**
     * Getter method for type
     *
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Getter method for password
     *
     * @return Returns the password.
     */
    public boolean isPassword()
    {
        return password;
    }

    /**
     * Getter method for required
     *
     * @return Returns the required.
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * Setter method for password
     *
     * @param password The password to set.
     */
    public void setPassword(boolean password)
    {
        this.password = password;
    }

    /**
     * Setter method for required
     *
     * @param required The required to set.
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }

    /**
     * Setter method for type
     *
     * @param type The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

}
