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
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIContext;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Jelly implemented page renderer
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JellyPageRenderer extends AbstractLogEnabled implements
        PageRenderer, Configurable
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

    private static String getScriptPath(String packageName, String page)
    {
        StringBuffer scriptPath = new StringBuffer();
        if (StringUtils.isNotEmpty(packageName))
        {
            scriptPath.append(packageName.replace('.', '/'));
            scriptPath.append("/");
        }
        scriptPath.append(page);
        if (!page.endsWith(".jelly"))
        {
            scriptPath.append(".jelly");
        }
        return scriptPath.toString();
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
        if (cacheSize > 1)
        {
            cacheScript = true;
            cache = new LRUMap(cacheSize);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String page)
    {
        String scriptPath = getScriptPath(packageName, page);
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
            if (cacheScript)
            {
                cache.put(path, entry);
            }
        }
        return script;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#render(com.cyclopsgroup.waterview.UIRuntime, java.lang.String, java.lang.String)
     */
    public void render(UIRuntime runtime, String packageName, String page)
            throws Exception
    {
        UIContext uic = runtime.getUIContext();
        JellyContext jc = (JellyContext) uic.get("jellyContext");
        if (jc == null)
        {
            jc = new JellyContext();
            String[] keys = uic.getKeys();
            for (int i = 0; i < keys.length; i++)
            {
                String name = keys[i];
                jc.setVariable(name, uic.get(name));
            }
            uic.put("jellyContext", jc);
        }
        XMLOutput output = (XMLOutput) uic.get("jellyOutput");
        if (output == null)
        {
            output = XMLOutput.createXMLOutput(runtime.getHttpServletResponse()
                    .getOutputStream());
            uic.put("jellyOutput", output);
        }
        String scriptPath = getScriptPath(packageName, page);
        Script script = getScript(scriptPath);
        script.run(jc, output);
        output.flush();
    }
}