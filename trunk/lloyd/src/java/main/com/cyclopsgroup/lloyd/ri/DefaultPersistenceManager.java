/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.lloyd.ri;

import java.util.Hashtable;

import javax.sql.DataSource;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.lloyd.PersistenceManager;
import com.cyclopsgroup.lloyd.PersistenceSession;
import com.cyclopsgroup.lloyd.Query;

/**
 * Default implementation of PersistenceManager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultPersistenceManager extends AbstractLogEnabled implements
        PersistenceManager
{
    private Hashtable dataSources = new Hashtable();

    /**
     * Override method createQuery in super class of DefaultPersistenceManager
     * 
     * @see com.cyclopsgroup.lloyd.PersistenceManager#createQuery(java.lang.Class)
     */
    public Query createQuery(Class objectClass)
    {
        return new Query(objectClass);
    }

    /**
     * Override method createQuery in super class of DefaultPersistenceManager
     * 
     * @see com.cyclopsgroup.lloyd.PersistenceManager#createQuery(java.lang.Class, java.lang.String)
     */
    public Query createQuery(Class objectClass, String queryName)
    {
        return new Query(objectClass);
    }

    /**
     * Override method getDataSource in super class of DefaultPersistenceManager
     * 
     * @see com.cyclopsgroup.lloyd.PersistenceManager#getDataSource(java.lang.String)
     */
    public DataSource getDataSource(String repoName)
    {
        return (DataSource) dataSources.get(repoName);
    }

    /**
     * Override method startSession in super class of DefaultPersistenceManager
     * 
     * @see com.cyclopsgroup.lloyd.PersistenceManager#startSession()
     */
    public PersistenceSession startSession()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method startSession in super class of DefaultPersistenceManager
     * 
     * @see com.cyclopsgroup.lloyd.PersistenceManager#startSession(java.lang.String)
     */
    public PersistenceSession startSession(String repoName)
    {
        // TODO Auto-generated method stub
        return null;
    }
}