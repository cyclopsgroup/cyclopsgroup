/*
 * Open Software License v2.1
 */
package com.cyclopsgroup.waterview.jelly;

import java.io.File;
import java.net.URL;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.LRUMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Resolver;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Jelly implemented page renderer
 *
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo</a>
 */
public class JellyPageRenderer extends AbstractLogEnabled implements Resolver,
        Configurable
{
    /**
      * This is a simple inner class that will be the element cahed in LRMMap.
      * Script object and timestamp of script resource is stored here
      */
    private class Entry
    {
        private Script script;

        private long timestamp;
    }

    private LRUMap cache;

    private boolean cacheScript = false;

    private JellyContext initialJellyContext = new JellyContext();

    private boolean reloadable = false;

    /**
     * Override method configure() in super class
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int cacheSize = conf.getChild("cache-size").getValueAsInteger(-1);
        reloadable = conf.getChild("reloadable").getValueAsBoolean(false);
        if (cacheSize > 2)
        {
            cacheScript = true;
            cache = new LRUMap(cacheSize);
        }
    }

    private synchronized Script getScript(String path) throws Exception
    {
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null)
        {
            return null;
        }
        long timestamp = -1;

        if (reloadable)
        {
            File resourceFile = new File(resource.getPath());

            if (resourceFile.isFile())
            {
                timestamp = resourceFile.lastModified();
            }
        }
        Script script = null;
        if (cacheScript)
        {
            Entry entry = (Entry) cache.get(path);
            if (entry != null)
            {
                if (reloadable)
                {
                    if (entry.timestamp >= timestamp)
                    {
                        script = entry.script;
                    }
                }
                else
                {
                    script = entry.script;
                }
            }
        }
        if (script == null)
        {
            script = initialJellyContext.compileScript(resource);

            Entry entry = new Entry();
            entry.script = script;
            entry.timestamp = timestamp;
            cache.put(path, entry);
        }
        return script;
    }

    /**
     * Override method isRenderer() in super class
     *
     * @see com.cyclopsgroup.waterview.Resolver#isRenderer()
     */
    public boolean isRenderer()
    {
        return true;
    }

    /**
     * Render the page and output the result into http response
     *
     * @see com.cyclopsgroup.waterview.Resolver#resolve(java.lang.String, com.cyclopsgroup.waterview.UIRuntime)
     */
    public void resolve(String path, UIRuntime runtime) throws Exception
    {
        JellyContext jellyContext = new JellyContext(initialJellyContext);
        XMLOutput output = XMLOutput.createXMLOutput(runtime
                .getHttpServletResponse().getWriter());
        Script script = getScript(path);
        script.run(jellyContext, output);
    }
}