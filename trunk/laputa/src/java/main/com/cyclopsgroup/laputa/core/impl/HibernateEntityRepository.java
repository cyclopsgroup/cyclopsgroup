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
package com.cyclopsgroup.laputa.core.impl;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.clib.site.hibernate.HibernateFactory;
import com.cyclopsgroup.laputa.core.EntityRepository;

/**
 * Hibernate implemented entity repository
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
class HibernateEntityRepository extends AbstractLogEnabled implements
        EntityRepository, Serviceable
{

    /**
     * 
     * @uml.property name="hibernateFactory"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    private HibernateFactory hibernateFactory;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.laputa.core.EntityRepository#delete(java.lang.Object)
     */
    public void delete(Object entity) throws Exception
    {
        hibernateFactory.getCurrentSession().delete(entity);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.laputa.core.EntityRepository#lookup(java.lang.Class, java.lang.String)
     */
    public Object lookup(Class type, String id) throws Exception
    {
        return hibernateFactory.getCurrentSession().load(type, id);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.laputa.core.EntityRepository#save(java.lang.Object)
     */
    public void save(Object entity) throws Exception
    {
        hibernateFactory.getCurrentSession().update(entity);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        hibernateFactory = (HibernateFactory) serviceManager
                .lookup(HibernateFactory.ROLE);
    }
}
