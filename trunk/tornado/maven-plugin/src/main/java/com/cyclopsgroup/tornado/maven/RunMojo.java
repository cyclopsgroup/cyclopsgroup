/* ==========================================================================
 * Copyright 2002-2006 Cyclops Group Software Foundation
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
package com.cyclopsgroup.tornado.maven;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.DefaultPlexusContainer;

import com.cyclopsgroup.tornado.Executable;
import com.cyclopsgroup.waterview.DefaultContext;
import com.cyclopsgroup.waterview.utils.ServiceManagerAdapter;

/**
 * @goal run
 * @description Mojo to run executable
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class RunMojo
    extends AbstractMojo
{
    /**
     * @description Class name of executable class
     * @required
     */
    private String className;

    /**
     * @description Plexus component descriptor file
     * @expression="${basedir}/src/main/webapp/WEB-INF/waterview-components.xml
     */
    private File componentDescriptor;

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        DefaultPlexusContainer container = new DefaultPlexusContainer();
        try
        {
            if ( componentDescriptor.isFile() )
            {
                getLog().info( "Pick up file " + componentDescriptor + " as plexus component descriptor" );
                container.setConfigurationResource( new FileReader( componentDescriptor ) );
            }
            container.initialize();
            container.start();

            DefaultContext context = new DefaultContext( System.getProperties() );

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Executable ex = (Executable) cl.loadClass( className ).newInstance();

            ex.execute( new ServiceManagerAdapter( container ), context );
        }
        catch ( Exception e )
        {
            throw new MojoExecutionException( "Plexus execution failed", e );
        }
        finally
        {
            container.dispose();
        }
    }
}