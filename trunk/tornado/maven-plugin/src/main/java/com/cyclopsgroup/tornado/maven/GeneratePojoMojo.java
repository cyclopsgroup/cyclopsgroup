/* ==========================================================================
 * Copyright 2002-2006 Cyclops Group Software Foundation
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
package com.cyclopsgroup.tornado.maven;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.tornado.hibernate.HibernateManager;
import com.cyclopsgroup.tornado.hibernate.HibernateService;

/**
 * @goal generate-pojo
 * @descriptionk Mojo to generate hibernate Pojos
 * @requiresDependencyResolution compile
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class GeneratePojoMojo
    extends AbstractHibernateMojoBase
{
    /**
     * @expression="false"
     * @description Generate EJB3 style entities
     */
    private boolean ejb3;

    /**
     * @expression="false"
     * @description Generate JDK5 style constructors
     */
    private boolean jdk5;

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.maven.AbstractHibernateMojoBase#execute(org.apache.avalon.framework.service.ServiceManager)
     */
    public void execute( ServiceManager serviceManager )
        throws Exception
    {
        HibernateManager hibernate = (HibernateManager) serviceManager.lookup( HibernateManager.ROLE );
        HibernateService hs = hibernate.getHibernateService( getHibernateName() );
    }
}
