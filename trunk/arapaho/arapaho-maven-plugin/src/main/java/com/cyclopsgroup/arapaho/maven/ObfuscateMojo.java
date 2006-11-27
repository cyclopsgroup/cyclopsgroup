package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @description Obfuscate generated jar file
 * @author <a href="mailto:jiaqi.guo@gmail.com">jiaqi</a>
 * @goal obfuscate
 */
public class ObfuscateMojo
    extends AbstractMojo
{
    /**
     * @parameter
     * @description Retro guard script path
     */
    private String retroScript;

    /**
     * @parameter expression="${basedir}/target/retroguard.log"
     * @required
     */
    private String retroLog;

    /**
     * @parameter expresison="${maven.final.name}"
     * @required
     */
    private String jarFile;

    /**
     * @see org.apache.maven.plugin.AbstractMojo#execute()
     */
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        URL retroJar = getClass().getClassLoader().getResource( "retroguard.jar" );
        if ( retroJar == null )
        {
            throw new IllegalStateException( "retroguard jar file is not found, plugin is corrupted" );
        }

        String script = retroScript;

        if ( StringUtils.isEmpty( script ) )
        {
            //TODO generate retro script based on source code directory
            //script = generated script 
        }

        ClassLoader cl = new URLClassLoader( new URL[] { retroJar } );

        File tempFile = new File( SystemUtils.getJavaIoTmpDir(), "obfuscated.jar" );
        try
        {
            Class<? extends Object> retroGuardClass = cl.loadClass( "RetroGuard" );
            Method mainMethod = retroGuardClass.getMethod( "main", new Class[] { String[].class } );
            mainMethod.invoke( null, new Object[] { jarFile, tempFile.getAbsoluteFile(), script, retroLog } );
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Run obfuscation failed", e );
        }
        finally
        {
            tempFile.delete();
        }
    }
}
