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
package com.cyclopsgroup.waterview.util;

import java.util.Hashtable;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

/**
 * Service manager for testing
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FakeServiceManager implements ServiceManager
{
    private Hashtable components = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.ServiceManager#hasService(java.lang.String)
     */
    public boolean hasService(String role)
    {
        return components.containsKey(role);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.ServiceManager#lookup(java.lang.String)
     */
    public Object lookup(String role) throws ServiceException
    {
        Object component = components.get(role);
        if (component == null)
        {
            throw new ServiceException(role, "Component doesn't exsit");
        }
        return component;
    }

    /**
     * Manually register a new component
     *
     * @param role Role of component
     * @param component Component object
     * @throws ServiceException Throw it out
     */
    public void registerComponent(String role, Object component)
            throws ServiceException
    {
        if (hasService(role))
        {
            throw new IllegalArgumentException("Role " + role
                    + " already exists");
        }
        if (component instanceof Serviceable)
        {
            ((Serviceable) component).service(this);
        }
        components.put(role, component);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.ServiceManager#release(java.lang.Object)
     */
    public void release(Object component)
    {
    }
}
