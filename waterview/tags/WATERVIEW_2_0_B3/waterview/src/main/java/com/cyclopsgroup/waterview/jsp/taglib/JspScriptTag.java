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
package com.cyclopsgroup.waterview.jsp.taglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jsp.JspEngine;
import com.cyclopsgroup.waterview.spi.JellyContextAdapter;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Jsp script tag
 */
public class JspScriptTag
    extends TagSupport
{
    private String path;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "path" );
        JspEngine je = (JspEngine) getServiceManager().lookup( JspEngine.ROLE );
        je.renderJsp( getPath(), getRuntimeData(), new JellyContextAdapter( getContext() ) );
        getRuntimeData().getOutput().flush();
    }

    /**
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path The path to set.
     */
    public void setPath( String path )
    {
        this.path = path;
    }
}
