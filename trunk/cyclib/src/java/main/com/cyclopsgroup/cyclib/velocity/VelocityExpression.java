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
package com.cyclopsgroup.cyclib.velocity;

import java.io.StringWriter;

import org.apache.velocity.app.Velocity;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Expression;

/**
 * Velocity implemented expression
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityExpression implements Expression
{

    private String logTag = "tag";

    private String velocityTemplate;

    /**
     * Constructor for class VelocityExpression
     *
     * @param template Velocity template
     */
    public VelocityExpression(String template)
    {
        this.velocityTemplate = template;
    }

    /**
     * Constructor for class VelocityExpression
     *
     * @param template Velocity template expression
     * @param logTag Log tag
     */
    public VelocityExpression(String template, String logTag)
    {
        this(template);
        this.logTag = logTag;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Expression#evaluate(com.cyclopsgroup.cyclib.Context)
     */
    public Object evaluate(Context context) throws Exception
    {
        VelocityContextAdapter vca = new VelocityContextAdapter(context);
        StringWriter sw = new StringWriter();
        Velocity.evaluate(vca, sw, logTag, velocityTemplate);
        sw.flush();
        return sw.toString();
    }
}