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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Jelly form control tag
 */
public class JellyFormControlTag
    extends TagSupport
    implements FormControlTag
{
    private String script;

    private FormTag formTag;

    /**
     * Getter method for field script
     *
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * Setter method for field script
     *
     * @param script The script to set.
     */
    public void setScript( String script )
    {
        this.script = script;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "script" );
        invokeBody( XMLOutput.createDummyXMLOutput() );

        if ( formTag == null )
        {
            throw new JellyTagException( "Form tag must be defined" );
        }
        JellyEngine je = (JellyEngine) getServiceManager().lookup( JellyEngine.ROLE );
        Script script = je.getScript( getScript() );

        JellyContext jc = new JellyContext( getContext() );
        jc.setVariable( "formTag", formTag );
        jc.setVariable( "form", formTag.getForm() );

        script.run( jc, output );
    }

    /**
     * Overwrite or implement method setFormTag()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.FormControlTag#setFormTag(com.cyclopsgroup.waterview.web.taglib.FormTag)
     */
    public void setFormTag( FormTag formTag )
        throws Exception
    {
        this.formTag = formTag;
    }
}