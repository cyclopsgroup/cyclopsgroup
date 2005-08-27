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
package com.cyclopsgroup.waterview.jelly;

import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.Waterview;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.ThemeManager;
import com.cyclopsgroup.waterview.spi.ThemeProvider;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.utils.BaseTagLibrary;
import com.cyclopsgroup.waterview.utils.TagPackage;

/**
 * Jelly engine for jelly processing
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyEngine extends AbstractLogEnabled implements Initializable,
        Contextualizable, Serviceable, DynaViewFactory, ThemeProvider
{
    /** Class name of definition tag package */
    private static final String DEFINITION_TAG_PACKAGE = "com.cyclopsgroup.waterview.jelly.deftaglib.DefinitionTagPackage";

    /** Definition taglib url */
    public static final String DEFINITION_TAGLIB_URL = "http://waterview.cyclopsgroup.com/definition";

    /** Stupid dummy script */
    public static final Script DUMMY_SCRIPT = new Script()
    {
        /**
         * Override or implement method of parent class or interface
         *
         * @see org.apache.commons.jelly.Script#compile()
         */
        public Script compile() throws JellyException
        {
            return this;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see org.apache.commons.jelly.Script#run(org.apache.commons.jelly.JellyContext, org.apache.commons.jelly.XMLOutput)
         */
        public void run(JellyContext jellyContext, XMLOutput output)
                throws JellyTagException
        {
            //doing nothing
        }
    };

    /** Role name of this class */
    public static final String ROLE = JellyEngine.class.getName();

    /** Name of service manager */
    public static final String SERVICE_MANAGER = ServiceManager.class.getName();

    private CacheManager cacheManager;

    private JellyContext globalContext;

    private Properties initProperties = new Properties();

    //private ModuleManager moduleManager;

    private ServiceManager serviceManager;

    private Hashtable tagLibraries = new Hashtable();

    private Hashtable themes = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(Context context) throws ContextException
    {
        try
        {
            Properties waterviewProperties = (Properties) context
                    .get(Waterview.INIT_PROPERTIES);
            initProperties.putAll(waterviewProperties);
        }
        catch (Exception e)
        {
            getLogger().debug("Can not add init properties", e);
        }
    }

    /**
     * Create new Jelly context with all variables in given context
     * 
     * @param context Given clib context
     * @return JellyContext object
     */
    public JellyContext createJellyContext(
            com.cyclopsgroup.waterview.Context context)
    {
        JellyContext jc = new JellyContext(getGlobalContext());
        for (Iterator i = context.keys(); i.hasNext();)
        {
            String name = (String) i.next();
            Object value = context.get(name);
            jc.setVariable(name, value);
        }
        return jc;
    }

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(com.cyclopsgroup.waterview.Path)
     */
    public View createView(Path path) throws Exception
    {
        Script script = getScript(path.getPackage(), path.getPath());
        return new JellyView(script, path.getFullPath());
    }

    /**
     * Getter method for cacheManager
     *
     * @return Returns the cacheManager.
     */
    public CacheManager getCacheManager()
    {
        return cacheManager;
    }

    /**
     * Getter method for globalContext
     *
     * @return Returns the globalContext.
     */
    public JellyContext getGlobalContext()
    {
        return globalContext;
    }

    /**
     * Getter method for initProperties
     *
     * @return Returns the initProperties.
     */
    public Properties getInitProperties()
    {
        return initProperties;
    }

    /**
     * Get script with give path and package name
     *
     * @param scriptPath Script path
     * @param packageName Look for it in this package
     * @return Script object
     * @throws JellyException Throw it out
     */
    public Script getScript(String packageName, String scriptPath)
            throws JellyException
    {
        return getScript(packageName, scriptPath, DUMMY_SCRIPT);
    }

    /**
     * Get script with give path and package name
     *
     * @param scriptPath Script path
     * @param packageName Look for it in this package
     * @param defaultScript Return as default value
     * @return Script object
     * @throws JellyException Throw it out
     */
    public Script getScript(String packageName, String scriptPath,
            Script defaultScript) throws JellyException
    {
        String scriptKey = scriptPath;
        if (StringUtils.isNotEmpty(packageName))
        {
            scriptKey = packageName + scriptPath;
        }
        Script script = null;
        synchronized (this)
        {
            if (getCacheManager().contains(this, scriptKey))
            {
                script = (Script) getCacheManager().get(this, scriptKey);
            }
            else
            {
                String fullPath = scriptPath;
                if (StringUtils.isNotEmpty(packageName))
                {
                    fullPath = '/' + packageName.replace('.', '/') + scriptPath;
                }
                URL resource = getClass().getClassLoader().getResource(
                        fullPath.substring(1));

                if (resource == null)
                {
                    script = DUMMY_SCRIPT;
                }
                else
                {
                    JellyContext jc = new JellyContext(getGlobalContext());
                    script = jc.compileScript(resource);
                }
                getCacheManager().put(this, scriptKey, script);
            }
        }
        return script == DUMMY_SCRIPT ? defaultScript : script;
    }

    /**
     * Overwrite or implement method getTheme()
     *
     * @see com.cyclopsgroup.waterview.spi.ThemeProvider#getTheme(java.lang.String)
     */
    public Theme getTheme(String themeName)
    {
        return (Theme) themes.get(themeName);
    }

    /**
     * Overwrite or implement method getThemeNames()
     *
     * @see com.cyclopsgroup.waterview.spi.ThemeProvider#getThemeNames()
     */
    public String[] getThemeNames()
    {
        return (String[]) themes.keySet()
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Init global context
     *
     * @throws Exception Throw it out
     */
    private void initGlobalContext() throws Exception
    {
        JellyContext jc = new JellyContext();
        jc.setVariable(SERVICE_MANAGER, serviceManager);
        jc.setVariable(ROLE, this);
        BaseTagLibrary deftaglib = new BaseTagLibrary();
        deftaglib.registerPackage((TagPackage) Class.forName(
                DEFINITION_TAG_PACKAGE).newInstance());
        jc.registerTagLibrary(DEFINITION_TAGLIB_URL, deftaglib);

        Enumeration e = getClass().getClassLoader().getResources(
                "META-INF/cyclopsgroup/waterview.xml");
        while (e.hasMoreElements())
        {
            URL resource = (URL) e.nextElement();
            jc.runScript(resource, XMLOutput.createDummyXMLOutput());
        }

        globalContext = new JellyContext();
        globalContext.setVariables(initProperties);
        for (Iterator i = tagLibraries.keySet().iterator(); i.hasNext();)
        {
            String uri = (String) i.next();
            BaseTagLibrary taglib = (BaseTagLibrary) tagLibraries.get(uri);
            globalContext.registerTagLibrary(uri, taglib);
        }
        globalContext.setVariable(SERVICE_MANAGER, serviceManager);
        globalContext.setVariable(ROLE, this);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        initGlobalContext();
    }

    /**
     * Register tag package into jelly engine
     *
     * @param uri Name space uri
     * @param tagPackage TagPackage object
     */
    public synchronized void registerTagPackage(String uri,
            TagPackage tagPackage)
    {
        BaseTagLibrary ctl = (BaseTagLibrary) tagLibraries.get(uri);
        if (ctl == null)
        {
            ctl = new BaseTagLibrary();
            tagLibraries.put(uri, ctl);
        }
        ctl.registerPackage(tagPackage);
    }

    /**
     * Register a theme
     *
     * @param theme Theme object
     */
    public void registerTheme(Theme theme)
    {
        themes.put(theme.getName(), theme);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
        cacheManager = (CacheManager) serviceManager.lookup(CacheManager.ROLE);
        ModuleManager moduleManager = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);

        String pattern = ".+\\.jelly";
        moduleManager.registerDynaViewFactory(pattern, this);

        ThemeManager tm = (ThemeManager) serviceManager
                .lookup(ThemeManager.ROLE);
        tm.registerThemeProvider(this);
    }
}
