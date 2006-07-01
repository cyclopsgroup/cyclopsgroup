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
package com.cyclopsgroup.tornado.impl.hibernate;

import org.apache.avalon.framework.service.ServiceManager;
import org.hibernate.dialect.Dialect;

import com.cyclopsgroup.tornado.Executable;
import com.cyclopsgroup.tornado.hibernate.HibernateManager;
import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.impl.sql.SqlHandler;
import com.cyclopsgroup.waterview.Context;

/**
 * Executable class to create database schema
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class CreateDatabaseSchema
    implements Executable
{
    private String hibernateName;

    private SqlHandler sqlHandler;

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.Executable#execute(org.apache.avalon.framework.service.ServiceManager, com.cyclopsgroup.waterview.Context)
     */
    public void execute( ServiceManager serviceManager, Context context )
        throws Exception
    {
        HibernateManager hm = (HibernateManager) serviceManager.lookup( HibernateManager.ROLE );
        HibernateService hs = (HibernateService) hm.getHibernateService( hibernateName );

        Dialect dialect = Dialect.getDialect( hs.getHibernateConfiguration().getProperties() );
        String[] sqls = hs.getHibernateConfiguration().generateSchemaCreationScript( dialect );
        sqlHandler.handleSqls( sqls, serviceManager, context );
    }
}