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
package com.cyclopsgroup.tornado.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate component
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface HibernateFactory
{
    /** Role name of this component */
    String ROLE = HibernateFactory.class.getName();

    /**
     * Close session if there is a session attached to current thread
     *
     * @throws Exception Throw it out
     */
    void closeCurrentSession() throws Exception;

    /**
     * Get hibernate session attached to current thread
     *
     * @return Session object. It's always returned
     * @throws Exception Throw it out
     */
    Session getCurrentSession() throws Exception;

    /**
     * Get session factory
     *
     * @return Hibernate session factory
     * @throws Exception Throw it out
     */
    SessionFactory getSessionFactory() throws Exception;

    /**
     * Check out if there is a session attached to current thread
     * @return True if there is a current session
     */
    boolean hasCurrentSession();
}
