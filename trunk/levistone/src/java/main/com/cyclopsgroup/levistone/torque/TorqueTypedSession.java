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

import org.apache.torque.om.Persistent;

import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.base.BaseTypedSession;

/**
 * Torque implemented typed session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorqueTypedSession extends BaseTypedSession
{
    private Connection dbcon;

    /**
     * Constructor for class TorqueTypedSession
     *
     * @param session
     * @param type
     * @param dbcon
     */
    public TorqueTypedSession(TorqueSession session, Class type,
            Connection dbcon)
    {
        super(session, type);
        this.dbcon = dbcon;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#create()
     */
    public Object create() throws PersistenceException
    {
        return create(null);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#create(java.lang.Object)
     */
    public Object create(Object id) throws PersistenceException
    {
        try
        {
            return getType().newInstance();
            //TODO set id
        }
        catch (Exception e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#delete(java.lang.Object)
     */
    public void delete(Object entity) throws PersistenceException
    {
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#deleteById(java.lang.Object)
     */
    public void deleteById(Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#evict(java.lang.Object)
     */
    public void evict(Object entity)
    {
        //do nothing
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#evictById(java.lang.Object)
     */
    public void evictById(Object id)
    {
        //do nothing
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#exists(java.lang.Object)
     */
    public boolean exists(Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#getId(java.lang.Object)
     */
    public Object getId(Object entity)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#lookup(java.lang.Object)
     */
    public Object lookup(Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.TypedSession#save(java.lang.Object)
     */
    public void save(Object entity) throws PersistenceException
    {
        if (entity instanceof Persistent)
        {
            Persistent p = (Persistent) entity;
            try
            {
                p.save(dbcon);
            }
            catch (Exception e)
            {
                throw new PersistenceException(e);
            }
        }
        else
        {
            throw new PersistenceException(
                    "Entity type is not torque-persistent");
        }
    }
}