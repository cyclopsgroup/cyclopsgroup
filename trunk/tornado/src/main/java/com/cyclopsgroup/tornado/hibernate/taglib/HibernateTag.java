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
package com.cyclopsgroup.tornado.hibernate.taglib;

import java.sql.Connection;

import org.apache.commons.jelly.XMLOutput;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author jiaqi
 *
 * Tag to provide hibernate environment
 */
public class HibernateTag
    extends TagSupport
{
    private String name = HibernateService.DEFAULT_DATASOURCE;

    private HibernateService hibernate;

    private boolean transaction;

    /**
     * Getter method for transaction
     *
     * @return Returns the transaction.
     */
    public boolean isTransaction()
    {
        return transaction;
    }

    /**
     * Setter method for transaction
     *
     * @param transaction The transaction to set.
     */
    public void setTransaction( boolean transaction )
    {
        this.transaction = transaction;
    }

    /**
     * Override method processTag in class HibernateTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        hibernate = (HibernateService) getServiceManager().lookup( HibernateService.ROLE );
        invokeBody( output );
    }

    /**
     * Get hibernate session
     * 
     * @return Hibernate session
     * @throws Exception From hibernate home
     */
    public Session getSession()
        throws Exception
    {
        return hibernate.getSession( getName(), isTransaction() );
    }

    /**
     * @return Hibernate configuartion
     */
    public Configuration getHibernateConfiguration()
    {
        return hibernate.getHibernateConfiguration( getName() );
    }

    /**
     * @return SQL connection related to this hibernate
     * @throws Exception Just throw it out
     */
    public Connection getConnection()
        throws Exception
    {
        return hibernate.getConnection( getName() );
    }

    /**
     * Getter method for name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }

}
