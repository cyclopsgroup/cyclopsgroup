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
package com.cyclopsgroup.clib.site.plexus;

import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBeanInfo;

import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;
import org.codehaus.plexus.component.manager.ComponentManager;
import org.codehaus.plexus.lifecycle.phase.AbstractPhase;

import com.cyclopsgroup.clib.site.jmx.DynamicMBeanProxy;
import com.cyclopsgroup.clib.site.jmx.MBeanServerFactory;

/**
 * Manage component with JMX
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ManagePhase extends AbstractPhase
{

    private static final String DOMAIN_NAME = "avaloncontainer";

    private static final String REGISTRY_KEY = ManagePhase.class.getName();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.lifecycle.phase.Phase#execute(java.lang.Object, org.codehaus.plexus.component.manager.ComponentManager)
     */
    public void execute(Object component, ComponentManager manager)
            throws Exception
    {
        if (!manager.getContainer().hasComponent(MBeanServerFactory.ROLE))
        {
            return;
        }
        MBeanServerFactory mbeanServerProvider = (MBeanServerFactory) manager
                .getContainer().lookup(MBeanServerFactory.ROLE);
        MBeanServer server = mbeanServerProvider.getMBeanServer();
        Registry registry = Registry.getRegistry(REGISTRY_KEY, null);
        ManagedBean manageBean = registry.findManagedBean(component.getClass(),
                null);
        ModelMBeanInfo mmi = manageBean.createMBeanInfo();
        MBeanInfo mbeanInfo = new MBeanInfo(DynamicMBeanProxy.class.getName(),
                mmi.getDescription(), mmi.getAttributes(),
                new MBeanConstructorInfo[0], mmi.getOperations(), mmi
                        .getNotifications());
        String fullname = DOMAIN_NAME + ":role=" + manager.getId();
        server.registerMBean(new DynamicMBeanProxy(component, mbeanInfo),
                new ObjectName(fullname));
    }
}
