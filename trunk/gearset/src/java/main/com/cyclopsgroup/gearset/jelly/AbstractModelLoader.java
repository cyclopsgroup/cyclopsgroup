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
package com.cyclopsgroup.gearset.jelly;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class AbstractModelLoader
{
    private Hashtable cache = new Hashtable();

    private JellyContext initialContext;

    /**
     * Create empty context with registries
     * 
     * @return Empty jelly context with libraries
     */
    protected abstract JellyContext createEmptyContext();

    /**
     * Get static initial context
     * 
     * @return Initial jelly context
     */
    public synchronized JellyContext getInitialContext()
    {
        if (initialContext == null)
        {
            initialContext = new JellyContext(createEmptyContext());
            initialContext.getVariables().putAll(System.getProperties());
            initialContext.setVariable("basedir", new File("")
                    .getAbsolutePath());
        }
        return initialContext;
    }

    /**
     * Get model object
     * 
     * @param fullName Full name of model
     * @return Model object
     * @throws Exception Any parsing error, not found error...
     */
    public synchronized Object getModel(String fullName) throws Exception
    {
        URL resource = getModelResource(fullName);
        Object model = null;
        if (cache.containsKey(fullName))
        {
            model = cache.get(fullName);
        }
        if (model == null)
        {
            JellyContext jc = new JellyContext(getInitialContext());
            MetaInfo meta = new MetaInfo(fullName, resource);
            jc.setVariable(MetaInfo.META_INFO_KEY, meta);
            JellyContext result = jc.runScript(resource, XMLOutput
                    .createDummyXMLOutput());
            model = result.getVariable(MetaInfo.MODEL_KEY);
            if (model != null)
            {
                cache.put(fullName, model);
            }
        }
        if (model == null)
        {
            throw new RuntimeException("Can't find model " + fullName);
        }
        return model;
    }

    /**
     * Get resource for given model name
     * 
     * @param fullName Model name
     * @return Resource object
     */
    public URL getModelResource(String fullName)
    {
        String path = fullName.replace('.', '/');
        path += ".xml";
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}