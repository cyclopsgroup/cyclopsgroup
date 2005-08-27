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
package com.cyclopsgroup.waterview.spi.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.utils.BaseTagSupport;

/**
 * Base jelly tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class BaseTag extends BaseTagSupport
{
    /**
     * Do the logic of this tag
     *
     * @param serviceManager Service manager object
     * @param output XMLOutput
     * @throws Exception Throw it out
     */
    protected abstract void doTag(ServiceManager serviceManager,
            XMLOutput output) throws Exception;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public final void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        ServiceManager serviceManager = (ServiceManager) getContext()
                .getVariable(ServiceManager.class.getName());
        try
        {
            doTag(serviceManager, output);
        }
        catch (JellyTagException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new JellyTagException(e);
        }
    }

    /**
     * Convenient method to get PageRuntime
     *
     * @return PageRuntime object
     */
    protected RuntimeData getRuntimeData()
    {
        return (RuntimeData) getContext().getVariable(RuntimeData.NAME);
    }
}