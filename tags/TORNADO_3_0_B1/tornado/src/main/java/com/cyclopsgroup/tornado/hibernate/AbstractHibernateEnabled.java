/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.hibernate;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Session;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base class that support hibernate
 */
public abstract class AbstractHibernateEnabled
    extends AbstractLogEnabled
    implements Serviceable
{
    private HibernateService hibernate;

    /**
     * Get hibernate service
     *
     * @return Hibernate service
     */
    protected HibernateService getHibernateService()
    {
        return hibernate;
    }

    /**
     * Get hibernate session
     *
     * @return Hibernate session
     * @throws Exception throw it out
     */
    protected Session getHibernateSession()
        throws Exception
    {
        return hibernate.getSession();
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        hibernate = (HibernateService) serviceManager.lookup( HibernateService.ROLE );
    }
}
