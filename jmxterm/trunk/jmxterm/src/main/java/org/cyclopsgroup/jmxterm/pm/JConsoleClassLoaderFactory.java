package org.cyclopsgroup.jmxterm.pm;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.lang.SystemUtils;

/**
 * Utility to get class loader that understands tools.jar and jconsole.jar
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class JConsoleClassLoaderFactory
{
    private JConsoleClassLoaderFactory()
    {
    }

    /**
     * @return ClassLoader that understands tools.jar and jconsole.jar
     * @throws IOException
     */
    public static ClassLoader getClassLoader()
    {
        File javaHome = new File( SystemUtils.JAVA_HOME ).getAbsoluteFile().getParentFile();
        File toolsJar, jconsoleJar;
        if ( SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX )
        {
            toolsJar = new File( javaHome, "Classes/classes.jar" );
            jconsoleJar = new File( javaHome, "Classes/jconsole.jar" );
        }
        else
        {
            toolsJar = new File( javaHome, "lib/tools.jar" );
            jconsoleJar = new File( javaHome, "lib/jconsole.jar" );
        }
        if ( !toolsJar.isFile() )
        {
            throw new RuntimeException( "Operation requires JDK instead of JRE" );
        }
        if ( !jconsoleJar.isFile() )
        {
            throw new RuntimeException( jconsoleJar + " file is not found" );
        }
        try
        {
            // Parent class loader has to be bootstrap class loader instead of current one
            return new URLClassLoader( new URL[] { toolsJar.toURI().toURL(), jconsoleJar.toURI().toURL() },
                                       String.class.getClassLoader() );
        }
        catch ( MalformedURLException e )
        {
            throw new RuntimeException( "Couddn't convert files to URLs " + toolsJar + ", " + jconsoleJar + ": "
                + e.getMessage(), e );
        }
    }
}