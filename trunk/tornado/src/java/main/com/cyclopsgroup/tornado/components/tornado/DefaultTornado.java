/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.tornado.components.tornado;

import java.net.URL;
import java.util.Enumeration;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.tornado.components.tornado.taglib.TornadoDefinitionTagLibrary;

/**
 * Default implementation of tornado
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultTornado extends AbstractLogEnabled implements Initializable
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        JellyContext jc = new JellyContext();
        jc.registerTagLibrary("http://tornado.cyclopsgroup.com/definition",
                new TornadoDefinitionTagLibrary());
        XMLOutput output = XMLOutput.createDummyXMLOutput();
        Enumeration e = getClass().getClassLoader().getResources(
                "META-INF/cyclopsgroup/tornado.xml");
        while (e.hasMoreElements())
        {
            URL resource = (URL) e.nextElement();
            jc.runScript(resource, output);
        }
    }
}
