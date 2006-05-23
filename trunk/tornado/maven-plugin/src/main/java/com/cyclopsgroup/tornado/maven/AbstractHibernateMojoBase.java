package com.cyclopsgroup.tornado.maven;

import java.io.File;
import java.io.FileReader;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.DefaultPlexusContainer;

import com.cyclopsgroup.waterview.utils.ServiceManagerAdapter;

/**
 * Base mojo for hibernate related mojos
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public abstract class AbstractHibernateMojoBase
    extends AbstractMojo
{
    /**
     * @parameter expression="default"
     * @description Name of the data source
     * @required
     */
    private String dataSource;

    /**
     * @parameter expression="${basedir}/src/main/java"
     * @description Directory where pojo java files should go
     * @required
     */
    private File pojoDirectory;

    /**
     * @parameter expression="${basedir}/src/main/resources"
     * @description Directory where hbm.xml files can be found
     * @required
     */
    private File schemaDirectory;

    /**
     * @description Service descriptor for plexus container
     */
    private File serviceDescriptor;

    /**
     * @parameter expression="${basedir}/target/sql"
     * @description Directory where sql files are generated
     * @required
     */
    private File sqlDirectory;

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        DefaultPlexusContainer plexusContainer = new DefaultPlexusContainer();
        try
        {
            if ( serviceDescriptor != null && serviceDescriptor.isFile() )
            {
                getLog().info( "Service descriptor is specified explicitly: " + serviceDescriptor );
                plexusContainer.setConfigurationResource( new FileReader( serviceDescriptor ) );
            }
            plexusContainer.initialize();
            plexusContainer.start();

            execute( new ServiceManagerAdapter( plexusContainer ) );
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Running plexus container fails", e );
        }
        finally
        {
            plexusContainer.dispose();
        }
    }

    protected abstract void execute( ServiceManager serviceManager )
        throws Exception;

    /**
     * Get name of the data source
     *
     * @return Data source name
     */
    protected String getDataSource()
    {
        return dataSource;
    }

    /**
     * Get directory where pojo java files go
     *
     * @return Usually src/main/java
     */
    protected File getPojoDirectory()
    {
        return pojoDirectory;
    }

    /**
     * Get schema directory
     *
     * @return Directory where schema file lies, usually src/main/resources
     */
    protected File getSchemaDirectory()
    {
        return schemaDirectory;
    }

    /**
     * Get directory where sql files are generated
     *
     * @return Directory where sql files are generated
     */
    protected File getSqlDirectory()
    {
        return sqlDirectory;
    }
}