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

import java.net.URL;

import org.apache.commons.jelly.JellyContext;

/**
 * Model meta info
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class MetaInfo
{
    /** Variable name of meta info object in context */
    public static final String META_INFO_KEY = "metaInfo";

    /** Variable name of model object in context */
    public static final String MODEL_KEY = "mainModel";

    /**
     * Method getInstance() in class MetaInfo
     * 
     * @param context
     * @return Instance of meta info
     */
    public static MetaInfo getInstance(JellyContext context)
    {
        return (MetaInfo) context.getVariable(MODEL_KEY);
    }

    private String fullName;

    private URL resource;

    /**
     * Constructor of MetaInfo
     * 
     * @param name
     * @param url
     */
    public MetaInfo(String name, URL url)
    {
        fullName = name;
        resource = url;
    }

    /**
     * Get resource of current model
     * 
     * @return URL resource
     */
    public URL getModelResource()
    {
        return resource;
    }

    /**
     * Get full name of model
     * 
     * @return Full name of model
     */
    public String getName()
    {
        return fullName;
    }
}