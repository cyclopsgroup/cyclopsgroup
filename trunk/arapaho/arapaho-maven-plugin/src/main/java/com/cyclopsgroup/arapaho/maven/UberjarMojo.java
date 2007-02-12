package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @description Create executable uber jar
 * @author <a href="mailto:jiaqi.guo@gmail.com>jiaqi</a>
 * @goal uberjar
 * @requiresDependencyResolution runtime
 */
public class UberjarMojo
    extends AbstractMojo
{
    /**
     * @parameter expression="${basedir}/target/${project.artifactId}-${project.version}.jar"
     * @required
     */
    private File artifactJarFile;

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    /**
     * @parameter expression="${basedir}/target/${project.artifactId}-uber-${project.version}.jar"
     * @required
     */
    private File uberjarFile;

    private void addFileEntry( JarOutputStream output, File file, String name )
        throws IOException
    {
        if ( !file.isFile() )
        {
            getLog().warn( "File " + file + " does not exist, skip it" );
            return;
        }

        ZipEntry entry = new ZipEntry( name );
        entry.setSize( file.length() );
        entry.setTime( file.lastModified() );
        output.putNextEntry( entry );
        FileInputStream input = new FileInputStream( file );
        getLog().info( "Adding " + entry.getName() );
        IOUtils.copy( input, output );
        input.close();
        output.flush();
    }

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        if ( !project.getPackaging().equals( "jar" ) )
        {
            getLog().error( "Uberjar is only for jar packaging project! Your project is " + project.getPackaging() );
            return;
        }

        if ( !artifactJarFile.isFile() )
        {
            getLog().error( artifactJarFile + " does not exist, please run mvn package first" );
            return;
        }

        if ( uberjarFile.isFile() )
        {
            uberjarFile.delete();
        }
        if ( !uberjarFile.getParentFile().isDirectory() )
        {
            uberjarFile.getParentFile().mkdirs();
        }

        try
        {
            FileOutputStream fileOutput = new FileOutputStream( uberjarFile );
            JarOutputStream output = new JarOutputStream( fileOutput );
            for ( Object classpathElement : project.getRuntimeClasspathElements() )
            {
                File dependency = new File( (String) classpathElement );
                if ( dependency.isFile() )
                {
                    addFileEntry( output, dependency, "WORLDS-INF/lib/" + dependency.getName() );
                }
            }
            addFileEntry( output, artifactJarFile, "WORLDS-INF/lib/" + artifactJarFile.getName() );
            output.flush();
            output.close();
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Execution exception", e );
        }
    }
}
