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
package com.cyclopsgroup.levistone.base;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.levistone.PersistenceManager;
import com.cyclopsgroup.levistone.Query;
import com.cyclopsgroup.levistone.QueryException;
import com.cyclopsgroup.levistone.QueryResult;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.TypedSession;

/**
 * Abstract base session implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class BaseSession implements Session
{

    /**
     * 
     * @uml.property name="id" 
     */
    private String id;

    /** Logger */
    protected Log logger = LogFactory.getLog(getClass());

    private String name;

    private PersistenceManager persistenceManager;

    private HashMap typedSessions = new HashMap();

    /**
     * Constructor of BaseSession
     * 
     * @param persistenceManager
     * @param name
     * @param id Id of this session
     */
    protected BaseSession(PersistenceManager persistenceManager, String name,
            String id)
    {
        this.persistenceManager = persistenceManager;
        this.name = name;
        this.id = id;
    }

    /**
     * Create typed session instance
     * 
     * @param type Type of this typed session
     * @return Typed session instance
     */
    protected abstract TypedSession createTypedSession(Class type);

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.levistone.Session#getId()
     * 
     * @uml.property name="id"
     */
    public String getId()
    {
        return id;
    }

    /**
     * Override method getName in super class of BaseSession
     * 
     * @see com.cyclopsgroup.levistone.Session#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Override method getPersistenceManager in super class of BaseSession
     * 
     * @see com.cyclopsgroup.levistone.Session#getPersistenceManager()
     */
    public PersistenceManager getPersistenceManager()
    {
        return persistenceManager;
    }

    /**
     * Override method getTypedSession in super class of BaseSession
     * 
     * @see com.cyclopsgroup.levistone.Session#getTypedSession(java.lang.Class)
     */
    public TypedSession getTypedSession(Class type)
    {
        if (typedSessions.containsKey(type))
        {
            return (TypedSession) typedSessions.get(type);
        }
        TypedSession typedSession = createTypedSession(type);
        typedSessions.put(type, typedSession);
        return typedSession;
    }

    /**
     * Override method lookup in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.Session#lookup(com.cyclopsgroup.levistone.Query)
     */
    public Object lookup(Query query) throws QueryException
    {
        QueryResult result = executeQuery(query);
        result.setLimit(1);
        List rs = result.list();
        return rs.isEmpty() ? null : rs.get(0);
    }
}