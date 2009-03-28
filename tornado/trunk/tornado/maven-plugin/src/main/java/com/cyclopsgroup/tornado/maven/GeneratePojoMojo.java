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

import org.apache.avalon.framework.service.ServiceManager;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2x.POJOExporter;

/**
 * @goal generate-pojo
 * @descriptionk Mojo to generate hibernate Pojos
 * @requiresDependencyResolution compile
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class GeneratePojoMojo
    extends AbstractHibernateMojoBase
{
    /**
     * @expression="false"
     * @description Generate EJB3 style entities
     */
    private boolean ejb3;

    /**
     * @expression="false"
     * @description Generate JDK5 style constructors
     */
    private boolean jdk5;

    /**
     * @expression="src/main/java"
     * @description The directory where POJOs are generated
     * @required
     */
    private File pojoDirectory;

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.maven.AbstractHibernateMojoBase#execute(org.apache.avalon.framework.service.ServiceManager)
     */
    public void execute( ServiceManager serviceManager )
        throws Exception
    {
        Configuration conf = new Configuration();
        conf.addDirectory( getSchemaDirectory() );

        POJOExporter exporter = new POJOExporter( conf, pojoDirectory );
        exporter.getProperties().setProperty( "jdk5", Boolean.valueOf( jdk5 ).toString() );
        exporter.getProperties().setProperty( "ejb3", Boolean.valueOf( ejb3 ).toString() );
        exporter.setTemplateName( "Pojo" );
        exporter.start();
    }
}
