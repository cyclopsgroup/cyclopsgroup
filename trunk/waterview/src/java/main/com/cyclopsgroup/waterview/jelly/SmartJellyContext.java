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
package com.cyclopsgroup.waterview.jelly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.jelly.JellyContext;

import com.cyclopsgroup.cyclib.Context;

/**
 * Smart jelly context who knows its children
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SmartJellyContext extends JellyContext
{
    private List children = new ArrayList();

    /**
     * Constructor for class SmartJellyContext
     *
     * @param parent Parent jelly context
     */
    public SmartJellyContext(JellyContext parent)
    {
        super(parent);
        if (parent instanceof SmartJellyContext)
        {
            ((SmartJellyContext) parent).addChild(this);
        }
    }

    /**
     * Add all variables in a given context
     *
     * @param context Given context
     */
    public void addAllVariables(Context context)
    {
        for (Iterator i = context.keys(); i.hasNext();)
        {
            String name = (String) i.next();
            setVariable(name, context.get(name));
        }
    }

    private void addChild(SmartJellyContext child)
    {
        children.add(child);
    }

    /**
     * Clear content of jelly context
     */
    public void clear()
    {
        for (Iterator i = children.iterator(); i.hasNext();)
        {
            SmartJellyContext child = (SmartJellyContext) i.next();
            child.clear();
        }
        children.clear();
        getVariables().clear();
    }
}