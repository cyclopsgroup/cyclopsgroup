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
package com.cyclopsgroup.levistone.jdbc;

import java.sql.Connection;

import com.cyclopsgroup.levistone.TypedSession;
import com.cyclopsgroup.levistone.base.BaseTypedSession;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JdbcTypedSession extends BaseTypedSession implements TypedSession
{
    private Connection dbcon;

    /**
     * Constructor of JdbcTypedSession
     * 
     * @param session
     * @param type
     * @param dbcon
     */
    JdbcTypedSession(JdbcSession session, Class type, Connection dbcon)
    {
        super(session, type);
        this.dbcon = dbcon;
    }

    /**
     * Override method create in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#create()
     */
    public Object create()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method create in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#create(java.lang.Object)
     */
    public Object create(Object id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method delete in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#delete(java.lang.Object)
     */
    public void delete(Object entity)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method deleteById in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#deleteById(java.lang.Object)
     */
    public void deleteById(Object id)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method evict in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#evict(java.lang.Object)
     */
    public void evict(Object entity)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method evictById in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#evictById(java.lang.Object)
     */
    public void evictById(Object id)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method exists in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#exists(java.lang.Object)
     */
    public boolean exists(Object id)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override method getId in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#getId(java.lang.Object)
     */
    public Object getId(Object entity)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method lookup in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#lookup(java.lang.Object)
     */
    public Object lookup(Object id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method save in super class of JdbcTypedSession
     * 
     * @see com.cyclopsgroup.levistone.TypedSession#save(java.lang.Object)
     */
    public void save(Object entity)
    {
        // TODO Auto-generated method stub

    }
}