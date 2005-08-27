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
package com.cyclopsgroup.tornado.utils;

import java.util.Properties;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Avalon utils
 */
public final class ConfigurationUtils
{
    /**
     * Get properties from given configuration node
     *
     * @param root Configuration node root
     * @return Properties object
     * @throws ConfigurationException Illegal format of configuration
     */
    public static Properties getProperties(Configuration root)
            throws ConfigurationException
    {
        Properties p = new Properties();
        Configuration[] confs = root.getChildren("property");
        for (int i = 0; i < confs.length; i++)
        {
            Configuration conf = confs[i];
            String name = conf.getAttribute("name");
            String value = conf.getValue(StringUtils.EMPTY);
            if (StringUtils.isNotEmpty(name))
            {
                p.setProperty(name, value);
            }
        }
        return p;
    }
}
