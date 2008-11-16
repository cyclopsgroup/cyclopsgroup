/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.jelly.taglib;

import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public abstract class BaseJellyControlTag
    extends TagSupport
{
    private String script;

    private static final String TAG_NAME = "controlTag";

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
     * Setter method for script
     *
     * @param script The script to set.
     */
    protected void setScript( String script )
    {
        this.script = script;
    }

    /**
     * Override method processTag in class JellyControlTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "script" );
        JellyEngine je = (JellyEngine) getServiceManager().lookup( JellyEngine.ROLE );
        Script s = je.getScript( getScript() );
        getContext().setVariable( TAG_NAME, this );
        runScript( s, output );
    }

    /**
     * Method to run script
     * 
     * @param script Script to run
     * @param output XMLOutput
     * @throws Exception Throw it out
     */
    protected void runScript( Script script, XMLOutput output )
        throws Exception
    {
        script.run( context, output );
    }
}