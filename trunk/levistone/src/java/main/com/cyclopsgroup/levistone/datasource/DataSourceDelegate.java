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
package com.cyclopsgroup.levistone.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

/**
 * Abstract data source adapter
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class DataSourceDelegate extends AbstractLogEnabled implements
        DataSource
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#getConnection()
     */
    public Connection getConnection() throws SQLException
    {
        return getNestedDataSource().getConnection();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
     */
    public Connection getConnection(String username, String password)
            throws SQLException
    {
        return getNestedDataSource().getConnection(username, password);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#getLoginTimeout()
     */
    public int getLoginTimeout() throws SQLException
    {
        return getNestedDataSource().getLoginTimeout();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#getLogWriter()
     */
    public PrintWriter getLogWriter() throws SQLException
    {
        return getNestedDataSource().getLogWriter();
    }

    /**
     * Overwrite this method to provide the concrete data source
     *
     * @return Real data source implementation
     * @throws SQLException Just throw it out
     */
    protected abstract DataSource getNestedDataSource() throws SQLException;

    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#setLoginTimeout(int)
     */
    public void setLoginTimeout(int seconds) throws SQLException
    {
        getNestedDataSource().setLoginTimeout(seconds);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
     */
    public void setLogWriter(PrintWriter out) throws SQLException
    {
        getNestedDataSource().setLogWriter(out);
    }
}