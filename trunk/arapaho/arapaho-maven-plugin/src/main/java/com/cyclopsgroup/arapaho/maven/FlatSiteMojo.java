package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * @description Generate site from flatsite templates
 * @goal flatsite
 */
public class FlatSiteMojo
    extends AbstractMojo
{
    /**
     * @parameter expression="${basedir}/src/flatsite
     * @required
     */
    private File flatsiteDirectory;

    /**
     * @parameter
     */
    private String layout;

    /**
     * @parameter expression="${basedir}/target/site
     * @required
     */
    private File outputDirectory;

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        if ( !flatsiteDirectory.isDirectory() )
        {
            getLog().info( flatsiteDirectory + " doesn't exist. There's nothing to do" );
            return;
        }
        if ( StringUtils.isEmpty( layout ) )
        {
            layout = "master_layout.vm";
        }
        if ( !outputDirectory.isDirectory() )
        {
            outputDirectory.mkdirs();
        }

        Properties props = new Properties();
        props.setProperty( "resource.loader", "__file__" );
        props.setProperty( "__file__.resource.loader.class",
                           "org.apache.velocity.runtime.resource.loader.FileResourceLoader" );
        props.setProperty( "__file__.resource.loader.path", flatsiteDirectory.getAbsolutePath() );

        VelocityEngine velocity = new VelocityEngine();
        try
        {
            velocity.init( props );
        }
        catch ( Exception e )
        {
            getLog().error( "Velocity error", e );
        }
    }
}