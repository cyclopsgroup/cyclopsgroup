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
package com.cyclopsgroup.tornado.sql;

import java.sql.Connection;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Data source factory interface
 */
public interface DataSourceService
{
    /** Empty data source array */
    DataSource[] EMPTY_DATASOURCE_ARRAY = new DataSource[0];

    void closeLocalConnection()
        throws Exception;

    /**
     * Get data source 
     *
     * @return Data source object
     */
    DataSource getDataSource();

    Connection getLocalConnection()
        throws Exception;
}