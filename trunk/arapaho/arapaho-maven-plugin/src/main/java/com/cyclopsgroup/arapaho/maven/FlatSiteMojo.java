package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

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
     * @parameter expression="${basedir}/target/site
     * @required
     */
    private File flatsiteOutputDirectory;

    /**
     * @parameter expression="${basedir}/src/flatsite
     * @required
     */
    private File flatsiteSourceDirectory;

    /**
     * @parameter expression="master_layout.vm"
     * @required
     */
    private String layout;

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    private VelocityEngine velocityEngine;

    private void copySiteFile( File sourceFile, String directory )
        throws IOException
    {
        File destDirectory = new File( flatsiteOutputDirectory, directory );
        if ( !destDirectory.isDirectory() )
        {
            getLog().info( "Making directory " + destDirectory );
            destDirectory.mkdirs();
        }
        getLog().info( "Copying file " + sourceFile + " to directory " + destDirectory );
        FileUtils.copyFileToDirectory( sourceFile, destDirectory );
    }

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        if ( !flatsiteSourceDirectory.isDirectory() )
        {
            getLog().info( flatsiteSourceDirectory + " doesn't exist. There's nothing to do" );
            return;
        }
        if ( !flatsiteOutputDirectory.isDirectory() )
        {
            getLog().info( "Makding destination directory " + flatsiteOutputDirectory );
            flatsiteOutputDirectory.mkdirs();
        }

        Properties props = new Properties();
        props.setProperty( "resource.loader", "__file__" );
        props.setProperty( "__file__.resource.loader.class", FileResourceLoader.class.getName() );
        props.setProperty( "__file__.resource.loader.path", flatsiteSourceDirectory.getAbsolutePath() );

        velocityEngine = new VelocityEngine();
        try
        {
            velocityEngine.init( props );
            generateSiteDirectory( "" );
        }
        catch ( Exception e )
        {
            getLog().error( "Velocity error", e );
        }
    }

    private void generateSiteDirectory( String relativeDirectory )
        throws IOException
    {
        File currentSourceDirectory = new File( flatsiteSourceDirectory, relativeDirectory );
        File[] files = currentSourceDirectory.listFiles();
        for ( File file : files )
        {
            if ( file.getName().charAt( 0 ) == '.' )
            {
                continue;
            }
            if ( file.isDirectory() )
            {
                generateSiteDirectory( mergePath( relativeDirectory, file.getName() ) );
            }
            else if ( file.getName().endsWith( "_layout.vm" ) )
            {
                continue;
            }
            else if ( file.getName().endsWith( ".vm" ) )
            {
                generateSiteFile( relativeDirectory, file.getName() );
            }
            else
            {
                copySiteFile( file, relativeDirectory );
            }
        }
    }

    private void generateSiteFile( String fileDirectory, String fileName )
    {
        String templatePath = mergePath( fileDirectory, fileName );
        File destDirectory = new File( flatsiteOutputDirectory, fileDirectory );
        if ( !destDirectory.isDirectory() )
        {
            getLog().info( "Making directory " + destDirectory );
            destDirectory.mkdirs();
        }
        String htmlFileName = fileName.substring( 0, fileName.length() - 3 ) + ".html";
        File htmlFile = new File( destDirectory, htmlFileName );

        Context context = new VelocityContext();
        context.put( "layout", layout );
        context.put( "pom", project );
        context.put( "title", project.getName() );
        context.put( "description", project.getDescription() );
        context.put( "templatePath", templatePath );
        context.put( "htmlPath", mergePath( fileDirectory, htmlFileName ) );

        String basedir;
        if ( StringUtils.isEmpty( fileDirectory ) )
        {
            basedir = ".";
        }
        else if ( fileDirectory.indexOf( '/' ) == -1 )
        {
            basedir = "..";
        }
        else
        {
            int levels = StringUtils.countMatches( fileDirectory, "/" );
            basedir = ".." + StringUtils.repeat( "/..", levels );
        }

        context.put( "basedir", basedir );

        try
        {
            StringWriter bodyWriter = new StringWriter();
            velocityEngine.mergeTemplate( templatePath, context, bodyWriter );

            String layoutTemplatePath = (String) context.get( "layout" );
            String body = bodyWriter.toString();
            context.put( "body", body );

            getLog().info(
                           "Generating " + htmlFile + " from template " + templatePath + " with layout "
                               + layoutTemplatePath );
            FileWriter output = new FileWriter( htmlFile );
            velocityEngine.mergeTemplate( layoutTemplatePath, context, output );
            output.flush();
            output.close();
        }
        catch ( Exception e )
        {
            getLog().warn( "Generating html file " + htmlFile + " failed", e );
        }
    }

    private String mergePath( String relativePath, String fileName )
    {
        return StringUtils.isEmpty( relativePath ) ? fileName : relativePath + "/" + fileName;
    }
}