package com.cyclopsgroup.tornado.impl.sql;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Startable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;

import com.cyclopsgroup.tornado.sql.AbstractDataSourceService;
import com.cyclopsgroup.tornado.sql.DataSourceService;
import com.cyclopsgroup.tornado.utils.ConfigurationUtils;

/**
 * DBCP version of data source service implementation
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DbcpDataSourceService
    extends AbstractDataSourceService
    implements DataSourceService, Configurable, Startable
{

    private BasicDataSource dataSource;

    private Properties properties;

    private boolean ready;

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        properties = ConfigurationUtils.getProperties( conf.getChild( "properties" ) );
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.sql.DataSourceService#getDataSource()
     */
    public DataSource getDataSource()
    {
        makeSureReady();
        return dataSource;
    }

    public int getNumActive()
    {
        makeSureReady();
        return dataSource.getNumActive();
    }

    public int getNumIdle()
    {
        makeSureReady();
        return dataSource.getNumIdle();
    }

    public Properties getProperties()
    {
        return properties;
    }

    public boolean isReady()
    {
        return ready;
    }

    private void makeSureReady()
    {
        if ( !isReady() )
        {
            throw new IllegalStateException( "DataSourceService is not ready yet" );
        }
    }

    /**
     * Set value programatically
     *
     * @param name Name of the property
     * @param value Value of the property
     */
    public void setProperty( String name, String value )
    {
        properties.setProperty( name, value );
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Startable#start()
     */
    public synchronized void start()
        throws Exception
    {
        dataSource = new BasicDataSource();
        BeanUtils.populate( dataSource, properties );
        ready = true;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Startable#stop()
     */
    public synchronized void stop()
        throws Exception
    {
        ready = false;
        if ( dataSource != null )
        {
            dataSource.close();
        }
        dataSource = null;
    }
}