package com.cyclopsgroup.arapaho.maven;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.arapaho.hibernate.DefaultHibernateService;

/**
 * @author <a href="mailto:jiaqi@amazon.com>jiaqi</a>
 */
public abstract class AbstractHibernateMojo
    extends AbstractMojo
{
    /**
     * @parameter
     */
    private String hibernateMeta;

    /**
     * @parameter expression="${basedir}/src/main/config/hibernate.properties"
     * @required
     */
    private File hibernateProperties;

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    protected void doExecute()
        throws Exception
    {
        final ClassLoader classLoader = ClassLoaderUtils.createProjectClassLoader( project
            .getRuntimeClasspathElements() );
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    DefaultHibernateService hibernateService;
                    if ( StringUtils.isEmpty( hibernateMeta ) )
                    {
                        hibernateService = new DefaultHibernateService();
                    }
                    else
                    {
                        hibernateService = new DefaultHibernateService( hibernateMeta );
                    }
                    hibernateService.setProperties( hibernateProperties.toURL() );
                    hibernateService.setProperties( System.getProperties() );
                    doExecute( (AnnotationConfiguration) hibernateService.getConfiguration() );
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
}