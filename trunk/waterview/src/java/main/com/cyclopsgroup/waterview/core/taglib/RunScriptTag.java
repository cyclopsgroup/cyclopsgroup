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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to run script
 */
public class RunScriptTag extends BaseTag
{
    private Script script;

    /**
     * Overwrite or implement method doTag()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        if (getScript() == null)
        {
            return;
        }
        getScript().run(getContext(), output);
    }

    /**
     * Getter method for property script
     *
     * @return Returns the script.
     */
    public Script getScript()
    {
        return script;
    }

    /**
     * Setter method for property script
     *
     * @param script The script to set.
     */
    public void setScript(Script script)
    {
        this.script = script;
    }
}
