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
package com.cyclopsgroup.levistone.torque;

import java.sql.Connection;
import java.util.Map;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.PersistenceManager;
import com.cyclopsgroup.levistone.Query;
import com.cyclopsgroup.levistone.QueryException;
import com.cyclopsgroup.levistone.QueryResult;
import com.cyclopsgroup.levistone.TypedSession;
import com.cyclopsgroup.levistone.base.BaseConnectionSession;

/**
 * Torque implemented session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorqueSession extends BaseConnectionSession
{

    /**
     * 
     * @uml.property name="closed" 
     */
    private boolean closed = false;


    /**
     * Constructor for class TorqueSession
     *
     * @param persistenceManager
     * @param name
     * @param id Id of session
     * @param dbcon
     */
    public TorqueSession(PersistenceManager persistenceManager, String name,
            String id, Connection dbcon)
    {
        super(persistenceManager, name, id, dbcon);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.base.BaseConnectionSession#createTypedSession(java.lang.Class, java.sql.Connection)
     */
    protected TypedSession createTypedSession(Class type, Connection dbcon)
    {
        return new TorqueTypedSession(this, type, dbcon);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.NamedQuery, java.util.Map)
     */
    public QueryResult executeQuery(NamedQuery query, Map attributes)
            throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.Query)
     */
    public QueryResult executeQuery(Query query) throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(java.lang.String)
     */
    public QueryResult executeQuery(String sqlQuery) throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#isClosed()
     */
    public boolean isClosed()
    {
        return closed;
    }

    /**
     * Setter method for closed
     * 
     * @param closed The closed to set.
     * 
     * @uml.property name="closed"
     */
    void setClosed(boolean closed) {
        this.closed = closed;
    }

}