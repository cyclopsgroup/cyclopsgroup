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

import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Data source which fetches another data source through JNDI
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JndiDataSource extends DelegateDataSourceProxy
{
    private DataSource nestedDataSource;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.datasource.DelegateDataSourceProxy#getNestedDataSource()
     */
    protected DataSource getNestedDataSource() throws SQLException
    {
        return nestedDataSource;
    }
}