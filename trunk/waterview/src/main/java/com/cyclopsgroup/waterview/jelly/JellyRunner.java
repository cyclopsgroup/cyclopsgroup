/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.jelly;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Waterview;
import com.cyclopsgroup.waterview.utils.WaterviewPlexusContainer;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class JellyRunner
{
    /**
     * Main entry to run a script
     * 
     * @param args Script paths
     * @throws Exception Throw it out
     */
    public static final void main(String[] args) throws Exception
    {
        List scripts = new ArrayList();
        for (int i = 0; i < args.length; i++)
        {
            String path = args[i];
            File file = new File(path);
            if (file.isFile())
            {
                scripts.add(file.toURL());
            }
            else
            {
                Enumeration enu = JellyRunner.class.getClassLoader()
                        .getResources(path);
                CollectionUtils.addAll(scripts, enu);
            }
        }
        if (scripts.isEmpty())
        {
            System.out.println("No script to run, return!");
            return;
        }

        String basedir = new File("").getAbsolutePath();
        Properties initProperties = new Properties(System.getProperties());
        initProperties.setProperty("basedir", basedir);
        initProperties.setProperty("plexus.home", basedir);

        WaterviewPlexusContainer container = new WaterviewPlexusContainer();
        for (Iterator j = initProperties.keySet().iterator(); j.hasNext();)
        {
            String initPropertyName = (String) j.next();
            container.addContextValue(initPropertyName, initProperties
                    .get(initPropertyName));
        }

        container.addContextValue(Waterview.INIT_PROPERTIES, initProperties);
        container.initialize();
        container.start();

        JellyEngine je = (JellyEngine) container.lookup(JellyEngine.ROLE);
        JellyContext jc = new JellyContext(je.getGlobalContext());

        for (Iterator i = scripts.iterator(); i.hasNext();)
        {
            URL script = (URL) i.next();
            System.out.print("Running script " + script);
            jc.runScript(script, XMLOutput.createDummyXMLOutput());
            System.out.println("... Done!");
        }
        container.dispose();
    }
}
