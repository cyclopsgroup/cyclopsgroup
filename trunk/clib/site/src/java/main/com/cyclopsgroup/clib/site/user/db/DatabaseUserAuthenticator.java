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
package com.cyclopsgroup.clib.site.user.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.clib.site.db.DataSourceFactory;
import com.cyclopsgroup.clib.site.user.User;
import com.cyclopsgroup.clib.site.user.UserAuthenticator;

/**
 * Database supported user authenticator
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DatabaseUserAuthenticator implements UserAuthenticator,
        Serviceable, Configurable, Initializable
{

    private DataSourceFactory dataSourceProvider;

    private String dataSourceProviderRole;

    private String passwordField;

    private ServiceManager serviceManager;

    private String tableName;

    private String userNameField;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#authenticate(java.lang.String, java.lang.String)
     */
    public boolean authenticate(String userName, String password)
            throws Exception
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        dataSourceProviderRole = conf.getChild("datasource-provider")
                .getAttribute("role", DataSourceFactory.ROLE);
        tableName = conf.getChild("tablename").getValue();

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#exsit(java.lang.String)
     */
    public boolean exsit(String userName) throws Exception
    {
        String sql = "SELECT " + userNameField + " FROM " + tableName
                + " WHERE " + userNameField + " = '" + userName + "'";
        Connection dbcon = getDataSource().getConnection();
        try
        {
            Statement statement = dbcon.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            boolean ret = rs.next();
            rs.close();
            statement.close();
            return ret;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            dbcon.close();
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#fetch(java.lang.String)
     */
    public User fetch(String userName) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    private DataSource getDataSource()
    {
        return dataSourceProvider.getDataSource();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        dataSourceProvider = (DataSourceFactory) serviceManager
                .lookup(dataSourceProviderRole);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
