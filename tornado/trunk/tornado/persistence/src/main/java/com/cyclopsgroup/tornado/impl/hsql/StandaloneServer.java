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
package com.cyclopsgroup.tornado.impl.hsql;

import java.io.File;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.activity.Startable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.hsqldb.Server;

/**
 * An in momery hsql database server
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class StandaloneServer
    extends AbstractLogEnabled
    implements Startable, Configurable, Initializable
{
    /**
     * Role name for this component
     */
    public static final String ROLE = StandaloneServer.class.getName();

    private Configuration config;

    private Server server;

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration config )
        throws ConfigurationException
    {
        this.config = config;
    }

    /**
     * Get HSQL server
     *
     * @return Server object
     */
    public Server getServer()
    {
        return server;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        server = new Server();
        Configuration[] dbConfs = config.getChild( "databases" ).getChildren( "database" );
        for ( int i = 0; i < dbConfs.length; i++ )
        {
            Configuration dbConf = dbConfs[i];
            String dbName = dbConf.getAttribute( "name" );
            String dbPath = dbConf.getAttribute( "path" );
            server.setDatabaseName( i, dbName );
            server.setDatabasePath( i, dbPath );

            File dbDir = new File( dbPath );
            if ( !dbDir.isDirectory() )
            {
                dbDir.mkdirs();
            }
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Startable#start()
     */
    public void start()
        throws Exception
    {
        server.start();
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Startable#stop()
     */
    public void stop()
        throws Exception
    {
        server.stop();
    }
}