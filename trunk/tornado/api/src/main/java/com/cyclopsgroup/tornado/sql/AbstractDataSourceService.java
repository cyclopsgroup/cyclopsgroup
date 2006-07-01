package com.cyclopsgroup.tornado.sql;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

/**
 * Abstract data source service
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public abstract class AbstractDataSourceService
    extends AbstractLogEnabled
    implements DataSourceService
{
    private ThreadLocal localConnection = new ThreadLocal();

    public synchronized void closeLocalConnection()
        throws Exception
    {
        Connection dbcon = (Connection) localConnection.get();
        if ( dbcon == null )
        {
            return;
        }

        if ( !dbcon.isClosed() )
        {
            dbcon.close();
        }

        localConnection.set( null );
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.sql.DataSourceService#getLocalConnection()
     */
    public Connection getLocalConnection()
        throws Exception
    {
        Connection dbcon = (Connection) localConnection.get();
        if ( dbcon == null || dbcon.isClosed() )
        {
            DataSource dataSource = getDataSource();
            synchronized ( this )
            {
                dbcon = (Connection) localConnection.get();
                if ( dbcon == null || dbcon.isClosed() )
                {
                    dbcon = dataSource.getConnection();
                    localConnection.set( dbcon );
                }
            }
        }
        return dbcon;
    }
}