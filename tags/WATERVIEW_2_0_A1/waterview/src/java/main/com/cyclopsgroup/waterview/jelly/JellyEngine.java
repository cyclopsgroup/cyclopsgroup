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
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.clib.lang.xml.ClibTagLibrary;
import com.cyclopsgroup.clib.lang.xml.TagPackage;
import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.DynaViewFactory;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.View;
import com.cyclopsgroup.waterview.Waterview;
import com.cyclopsgroup.waterview.utils.Path;

/**
 * Jelly engine for jelly processing
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyEngine extends AbstractLogEnabled implements Initializable,
        Contextualizable, Serviceable, DynaViewFactory, ActionResolver
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

    /** Name constant of jelly context */
    public static final String JELLY_CONTEXT = JellyContext.class.getName();

    /** Name constant of jelly output */
    public static final String JELLY_OUTPUT = XMLOutput.class.getName();

    /** Rendering for switch */
    public static final String RENDERING = "rendering";

    /** Role name of this class */
    public static final String ROLE = JellyEngine.class.getName();

    /** Name of service manager */
    public static final String SERVICE_MANAGER = ServiceManager.class.getName();

    /**
     * Pass all variables into jelly context from context
     *
     * @param context Clib context
     * @param jc Jelly context
     */
    public static final void passVariables(
            com.cyclopsgroup.clib.lang.Context context, JellyContext jc)
    {
        for (Iterator i = context.keys(); i.hasNext();)
        {
            String name = (String) i.next();
            jc.setVariable(name, context.get(name));
        }
        jc.setVariable("context", context);
    }

    /**
     * Pass all variables from jelly context to clib context
     *
     * @param jc Jelly context
     * @param context Clib context
     */
    public static final void passVariables(JellyContext jc,
            com.cyclopsgroup.clib.lang.Context context)
    {
        for (Iterator i = jc.getVariableNames(); i.hasNext();)
        {
            String name = (String) i.next();
            if (StringUtils.equals("context", name))
            {
                continue;
            }
            context.put(name, jc.getVariable(name));
        }
    }

    private JellyContext globalContext;

    private Properties initProperties = new Properties();

    private ServiceManager serviceManager;

    private Hashtable tagLibraries = new Hashtable();

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
            if (waterviewProperties != null)
            {
                initProperties.putAll(waterviewProperties);
            }
        }
        catch (Exception e)
        {
            getLogger().warn("Can not add init properties");
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.DynaViewFactory#createView(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public View createView(String viewPath, PageRuntime runtime)
            throws Exception
    {
        String path = "view/" + viewPath;
        Script script = getScript(path);
        ModuleManager mm = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        return new ScriptView(script, mm.getModule(path));
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
     * Get script with script path
     *
     * @param scriptPath Path of the script
     * @return Script object or DUMMY_SCRIPT
     * @throws Exception Throw it out
     */
    public Script getScript(String scriptPath) throws Exception
    {
        return getScript(scriptPath, DUMMY_SCRIPT);
    }

    /**
     * Get script with given path
     *
     * @param scriptPath Path of script
     * @param defaultScript Script in case it's not found
     * @return Script object
     * @throws Exception Throw it out
     */
    public Script getScript(String scriptPath, Script defaultScript)
            throws Exception
    {
        ModuleManager mm = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        String[] packageNames = mm.getPackageNames();
        Script script = defaultScript;
        for (int i = 0; i < packageNames.length; i++)
        {
            Script s = getScript(scriptPath, packageNames[i], null);
            if (s != null)
            {
                script = s;
                break;
            }
        }
        return script;
    }

    /**
     * Get script with give path and package name
     *
     * @param scriptPath Script path
     * @param packageName Look for it in this package
     * @return Script object
     * @throws JellyException Throw it out
     */
    public Script getScript(String scriptPath, String packageName)
            throws JellyException
    {
        return getScript(scriptPath, packageName, DUMMY_SCRIPT);
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
    public Script getScript(String scriptPath, String packageName,
            Script defaultScript) throws JellyException
    {
        if (!scriptPath.endsWith(".jelly"))
        {
            Path pr = Path.parse(scriptPath);
            scriptPath = pr.getParentPath() + pr.getShortName() + ".jelly";
        }
        String fullPath = scriptPath;
        if (StringUtils.isNotEmpty(packageName))
        {
            fullPath = packageName.replace('.', '/') + '/' + scriptPath;
        }
        URL resource = getClass().getClassLoader().getResource(fullPath);
        if (resource == null)
        {
            return defaultScript;
        }
        JellyContext jc = new JellyContext(getGlobalContext());
        return jc.compileScript(resource);
    }

    /**
     * Init global context
     *
     * @throws Exception Throw it out
     */
    public void initGlobalContext() throws Exception
    {
        JellyContext jc = new JellyContext();
        jc.setVariable(SERVICE_MANAGER, serviceManager);
        jc.setVariable(ROLE, this);
        ClibTagLibrary deftaglib = new ClibTagLibrary();
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
            ClibTagLibrary taglib = (ClibTagLibrary) tagLibraries.get(uri);
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
        ClibTagLibrary ctl = (ClibTagLibrary) tagLibraries.get(uri);
        if (ctl == null)
        {
            ctl = new ClibTagLibrary();
            tagLibraries.put(uri, ctl);
        }
        ctl.registerPackage(tagPackage);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ActionResolver#resolveAction(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void resolveAction(String action, PageRuntime runtime) throws Exception
    {
        Script script = getScript("action/" + action);
        if (script == null)
        {
            return;
        }
        JellyContext jc = new JellyContext(getGlobalContext());
        passVariables(runtime.getPageContext(), jc);
        script.run(jc, XMLOutput.createDummyXMLOutput());
        passVariables(jc, runtime.getPageContext());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
