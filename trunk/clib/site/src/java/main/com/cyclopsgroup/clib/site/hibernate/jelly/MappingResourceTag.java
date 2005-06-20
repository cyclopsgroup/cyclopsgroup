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
package com.cyclopsgroup.clib.site.hibernate.jelly;

import java.net.URL;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;

import com.cyclopsgroup.clib.lang.xml.ClibTagSupport;

public class MappingResourceTag extends ClibTagSupport
{
    private Log logger = LogFactory.getLog(getClass());

    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        String path = getBodyText();
        try
        {
            URL resource = getClass().getClassLoader().getResource(path);
            if (path == null)
            {
                return;
            }
            Configuration hibernateConfiguration = (Configuration) getContext()
                    .getVariable(Configuration.class.getName());
            hibernateConfiguration.addInputStream(resource.openStream());
        }
        catch (Exception e)
        {
            logger.error("Invalid tag definition mapping file url = " + path);
        }
    }

}
