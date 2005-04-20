/*
 * Open Software License v2.1
 */
package com.cyclopsgroup.waterview.jelly;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.jelly.PropertyTagRegistry;
import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Jelly implemented page renderer
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JellyPageRenderer extends AbstractLogEnabled implements
        PageRenderer, Configurable, Initializable
{

    /**
     * This is a simple inner class that will be the element cahed in LRMMap. Script object and timestamp of script resource is stored here
     */
    private class Entry
    {
        private Script script;

        private long timestamp;
    }

    private static final String CONTENT_TYPE = "text/html";

    private static String getScriptPath(String packageName, String module)
    {
        StringBuffer scriptPath = new StringBuffer();
        if (StringUtils.isNotEmpty(packageName))
        {
            scriptPath.append(packageName.replace('.', '/'));
            scriptPath.append('/');
        }
        scriptPath.append(module).append(".jelly");
        return scriptPath.toString();
    }

    private Map cache;

    private JellyContext initialJellyContext;

    private boolean reloadable = false;

    /**
     * Manually clear cache
     */
    public void clearCache()
    {
        if (cache != null)
        {
            cache.clear();
        }
    }

    /**
     * Override method configure() in super class
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int cacheSize = conf.getChild("cache-size").getValueAsInteger(-1);
        reloadable = conf.getChild("reloadable").getValueAsBoolean(false);
        if (cacheSize >= 1)
        {
            cache = new LRUMap(cacheSize);
        }
        else if (cacheSize == 0)
        {
            cache = null;
        }
        else
        {
            cache = new Hashtable();
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.PageRenderer#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String module)
    {
        String scriptPath = getScriptPath(packageName, module);
        try
        {
            return getScript(scriptPath) != null;
        }
        catch (Exception e)
        {
            getLogger().warn("Load script error", e);
            return false;
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.PageRenderer#getContentType()
     */
    public String getContentType()
    {
        return CONTENT_TYPE;
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
        if (cache != null)
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
            if (cache != null)
            {
                cache.put(path, entry);
            }
        }
        return script;
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        initialJellyContext = new PropertyTagRegistry(
                "META-INF/cyclopsgroup/waterview-tag-registry.properties")
                .createContext();
    }

    /**
     * Override method render in super class of JellyPageRenderer
     * 
     * @see com.cyclopsgroup.waterview.PageRenderer#render(com.cyclopsgroup.cyclib.Context, java.lang.String, java.lang.String, com.cyclopsgroup.waterview.UIRuntime)
     */
    public void render(Context context, String packageName, String module,
            UIRuntime runtime) throws Exception
    {
        String scriptPath = getScriptPath(packageName, module);
        Script script = getScript(scriptPath);
        /*
         if (script == null)
         {
         return;
         }*/
        XMLOutput jellyOutput = (XMLOutput) context.get("jellyOutput");
        if (jellyOutput == null)
        {
            jellyOutput = XMLOutput.createXMLOutput(runtime.getOutput());
            context.put("jellyOutput", jellyOutput);
        }

        JellyContext jc = new JellyContext(initialJellyContext);
        for (Iterator i = context.keys(); i.hasNext();)
        {
            String name = (String) i.next();
            jc.setVariable(name, context.get(name));
        }
        synchronized (script)
        {
            try
            {
                script.run(jc, jellyOutput);
                runtime.getOutput().flush();
            }
            catch (Exception e)
            {
                throw e;
            }
            finally
            {
                jc.getVariables().clear();
            }
        }
    }
}