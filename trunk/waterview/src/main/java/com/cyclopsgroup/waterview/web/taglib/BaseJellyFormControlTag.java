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

import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Jelly form control tag
 */
public abstract class BaseJellyFormControlTag
    extends BaseJellyControlTag
    implements FormControlTag
{
    private FormTag formTag;

    /**
     * Override method runScript in class BaseJellyFormControlTag
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag#runScript(org.apache.commons.jelly.Script, org.apache.commons.jelly.XMLOutput)
     */
    protected void runScript( Script script, XMLOutput output )
        throws Exception
    {
        invokeBody( XMLOutput.createDummyXMLOutput() );
        if ( formTag == null )
        {
            throw new JellyTagException( "Form tag must be defined" );
        }
        JellyContext jc = new JellyContext( getContext() );
        jc.setVariable( "formControl", this );
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