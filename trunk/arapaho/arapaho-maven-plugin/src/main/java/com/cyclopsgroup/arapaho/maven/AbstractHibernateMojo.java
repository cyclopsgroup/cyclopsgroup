package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author <a href="mailto:jiaqi@amazon.com>jiaqi</a>
 */
public abstract class AbstractHibernateMojo
    extends AbstractMojo
{
    private static final String RESOURCE_PREFIX = "resource:";

    /**
     * @pareameter
     */
    private String configFile;

    /**
     * @parameter
     */
    private List<String> configFiles = new ArrayList<String>();

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    protected void doExecute()
        throws Exception
    {
        List<URL> urls = new ArrayList<URL>();
        for ( Object pathString : project.getRuntimeClasspathElements() )
        {
            File pathElement = new File( (String) pathString );
            urls.add( pathElement.toURL() );
        }

        final URLClassLoader classLoader = new URLClassLoader( urls.toArray( Constants.EMPTY_URL_ARRAY ), getClass()
            .getClassLoader() );

        final AnnotationConfiguration config = new AnnotationConfiguration();

        List<String> allConfigFiles = new ArrayList<String>( configFiles );
        if ( StringUtils.isNotEmpty( configFile ) )
        {
            allConfigFiles.add( configFile );
        }

        for ( String filePath : configFiles )
        {
            filePath = filePath.trim();

            if ( filePath.startsWith( RESOURCE_PREFIX ) )
            {
                String resourcePath = filePath.substring( RESOURCE_PREFIX.length() );
                URL resource = classLoader.getResource( resourcePath );
                if ( resource == null )
                {
                    getLog().warn( "Config " + resourcePath + " doesn't exist in runtime classpath" );
                }
                else
                {
                    populateConfiguration( config, resource );
                }
            }
            else
            {
                File file = new File( filePath );
                if ( file.exists() )
                {
                    populateConfiguration( config, file.toURL() );
                }
                else
                {
                    getLog().warn( "Specified config file " + filePath + " doesn't exist" );
                }
            }
        }
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    doExecute( config );
                }
                catch ( Exception e )
                {
                    getLog().error( "Run mojo error", e );
                }
            }
        };

        thread.setContextClassLoader( classLoader );
        thread.start();
        thread.join();
    }

    protected abstract void doExecute( AnnotationConfiguration config )
        throws Exception;

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        try
        {
            doExecute();
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Execution error", e );
        }
    }

    private void populateConfiguration( Configuration config, URL configResource )
        throws IOException
    {
        if ( configResource.getFile().endsWith( ".properties" ) )
        {
            Properties props = new Properties();
            props.load( configResource.openStream() );
            config.setProperties( props );
        }
        else
        {
            config.configure( configResource );
        }
    }
}