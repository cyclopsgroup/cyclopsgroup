package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public final class ClassLoaderUtils
{
    private static URL[] EMPTY_URL_ARRAY = new URL[0];

    public static ClassLoader createProjectClassLoader( List<String> classpathElements )
        throws MalformedURLException
    {
        return createProjectClassLoader( classpathElements, ClassLoaderUtils.class.getClassLoader() );
    }

    public static ClassLoader createProjectClassLoader( List<String> classpathElements, ClassLoader parent )
        throws MalformedURLException
    {
        List<URL> urls = new ArrayList<URL>();
        for ( Object pathString : classpathElements )
        {
            File pathElement = new File( (String) pathString );
            urls.add( pathElement.toURL() );
        }

        return new URLClassLoader( urls.toArray( ClassLoaderUtils.EMPTY_URL_ARRAY ), parent );
    }
}
