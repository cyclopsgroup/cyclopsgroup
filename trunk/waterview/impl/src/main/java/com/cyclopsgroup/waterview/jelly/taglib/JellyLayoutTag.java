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
package com.cyclopsgroup.waterview.jelly.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.jelly.JellyLayout;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Jelly layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyLayoutTag
    extends TagSupport
{
    private String script;

    /**
     * Getter method for script
     *
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    public void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "script" );
        Page page = (Page) context.getVariable( Page.NAME );
        if ( page == null )
        {
            throw new JellyTagException( "JellyLayout must be in a page" );
        }
        JellyEngine jellyEngine = (JellyEngine) getServiceManager().lookup( JellyEngine.ROLE );
        Layout layout = new JellyLayout( jellyEngine.getScript( getScript() ), getScript() );
        page.setLayout( layout );
    }

    /**
     * Setter method for script
     *
     * @param script The script to set.
     */
    public void setScript( String script )
    {
        this.script = script;
    }
}
