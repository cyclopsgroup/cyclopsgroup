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
package com.cyclopsgroup.tornado.impl.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.tornado.sql.DataSourceService;
import com.cyclopsgroup.waterview.Context;

/**
 * Execute sqls
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class SqlQueryExecutor
    implements SqlHandler
{
    private DataSourceService dataSourceService;

    /**
     * Constructor for class SqlQueryExecutor
     *
     * @param dataSource dataSource name
     */
    public SqlQueryExecutor( DataSourceService dataSourceService )
    {
        this.dataSourceService = dataSourceService;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.impl.sql.SqlHandler#handleSqls(java.lang.String[], org.apache.avalon.framework.service.ServiceManager, com.cyclopsgroup.waterview.Context)
     */
    public void handleSqls( String[] sqls, ServiceManager serviceManager, Context context )
        throws Exception
    {
        Connection dbcon = dataSourceService.getLocalConnection();

        try
        {
            Statement statement = dbcon.createStatement();
            for ( int i = 0; i < sqls.length; i++ )
            {
                String sql = sqls[i];
                statement.addBatch( sql );
            }
            statement.executeBatch();
            statement.close();
        }
        catch ( SQLException e )
        {
            throw e;
        }
        finally
        {
            dbcon.close();
        }
    }
}