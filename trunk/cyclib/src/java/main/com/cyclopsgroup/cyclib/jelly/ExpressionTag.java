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
package com.cyclopsgroup.cyclib.jelly;

import java.io.StringWriter;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Expression;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ExpressionTag extends TagSupport implements Expression
{
    private Script bodyScript;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        try
        {
            bodyScript = getBody().compile();
        }
        catch (JellyTagException e)
        {
            throw e;
        }
        catch (JellyException e)
        {
            throw new JellyTagException("", e);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Expression#evaluate(com.cyclopsgroup.cyclib.Context)
     */
    public Object evaluate(Context ctx) throws Exception
    {
        JellyContext jc = new JellyContext(context);
        String[] names = ctx.getNames();
        for (int i = 0; i < names.length; i++)
        {
            String name = names[i];
            jc.setVariable(name, ctx.get(name));
        }
        StringWriter sw = new StringWriter();
        bodyScript.run(jc, XMLOutput.createXMLOutput(sw));
        return sw.toString();
    }
}