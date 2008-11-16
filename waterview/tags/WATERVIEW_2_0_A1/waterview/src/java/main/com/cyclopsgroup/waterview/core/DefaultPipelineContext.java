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
package com.cyclopsgroup.waterview.core;

import java.util.List;

import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

/**
 * Default implementation of pipeline context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultPipelineContext implements PipelineContext
{
    private int position = 0;

    private List valves;

    /**
     * Constructor for class DefaultPipelineContext
     *
     * @param valveList List of valves
     */
    public DefaultPipelineContext(List valveList)
    {
        valves = valveList;
    }

    /**
     * Getter method for position
     *
     * @return Returns the position.
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PipelineContext#invokeNextValve(com.cyclopsgroup.waterview.PageRuntime)
     */
    public void invokeNextValve(PageRuntime runtime) throws Exception
    {
        position++;
        if (position >= valves.size())
        {
            return;
        }
        invokeValve(runtime);
    }

    /**
     * Invoke current valve
     *
     * @param runtime Page runtime object
     * @throws Exception Throw it out
     */
    public void invokeValve(PageRuntime runtime) throws Exception
    {
        Valve valve = (Valve) valves.get(position);
        valve.invoke(runtime, this);
    }

    /**
     * Setter method for position
     *
     * @param position The position to set.
     */
    public void setPosition(int position)
    {
        this.position = position;
    }
}
