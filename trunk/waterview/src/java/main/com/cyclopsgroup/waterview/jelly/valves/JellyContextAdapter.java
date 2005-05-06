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
package com.cyclopsgroup.waterview.jelly.valves;

import java.util.Iterator;

import org.apache.commons.jelly.JellyContext;

import com.cyclopsgroup.clib.lang.Context;

/**
 * Clib context adapter for Jelly context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyContextAdapter implements Context
{
    private JellyContext jellyContext;

    /**
     * Constructor for class JellyContextAdapter
     *
     * @param jellyContext Core jelly context
     */
    public JellyContextAdapter(JellyContext jellyContext)
    {
        this.jellyContext = jellyContext;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        return jellyContext.getVariable(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#keys()
     */
    public Iterator keys()
    {
        return jellyContext.getVariableNames();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object value)
    {
        jellyContext.setVariable(name, value);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#remove(java.lang.String)
     */
    public void remove(String name)
    {
        jellyContext.removeVariable(name);
    }
}
