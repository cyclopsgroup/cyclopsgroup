/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
 *
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.mortbay.jetty.Server;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * @description Run jetty servlet container
 * @goal run-jetty
 * @requiresDependencyResolution test
 */
public class RunJettyMojo
    extends AbstractMojo
{
    private static final URL[] EMPTY_URL_ARRAY = new URL[0];

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    /**
     * @parameter expression="${basedir}/src/test/config/jetty.xml"
     * @required
     */
    private String jettyXml;

    /**
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        if ( !new File( jettyXml ).isFile() )
        {
            throw new MojoExecutionException( "Jetty xml file " + jettyXml
                + " is invalid, check your jettyXml parameter" );
        }

        List<URL> urls = new ArrayList<URL>();
        try
        {
            for ( Object pathString : project.getTestClasspathElements() )
            {
                File pathElement = new File( (String) pathString );
                urls.add( pathElement.toURL() );
            }
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Dependency problem", e );
        }

        final URLClassLoader classLoader = new URLClassLoader( urls.toArray( EMPTY_URL_ARRAY ), getClass()
            .getClassLoader() );

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Server.main( new String[] { jettyXml } );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        };

        thread.setContextClassLoader( classLoader );

        thread.start();
        try
        {
            thread.join();
        }
        catch ( InterruptedException e )
        {
            getLog().info( "The thread is interruptted" );
        }
    }
}
