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
package com.cyclopsgroup.waterview;

import java.util.LinkedList;

import org.apache.commons.collections.ExtendedProperties;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class Waterview
{
    private LinkedList valves = new LinkedList();

    /**
     * Method addValve() in class Pipeline
     * 
     * @param valve
     */
    public void addValve(WaterviewValve valve)
    {
        valves.add(valve);
    }

    /**
     * Method getValves() in class Pipeline
     * 
     * @return
     */
    public WaterviewValve[] getValves()
    {
        return (WaterviewValve[]) valves.toArray(WaterviewValve.EMPTY_ARRAY);
    }

    /**
     * Method init() in class WaterviewPipeline
     * 
     * @param props
     * @throws Exception
     */
    public void init(ExtendedProperties props) throws Exception
    {
        String[] valveClassNames = props.getStringArray("pipeline.valve");
        for (int i = 0; i < valveClassNames.length; i++)
        {
            String valveClassName = valveClassNames[i];
            try
            {
                WaterviewValve valve = (WaterviewValve) Class.forName(
                        valveClassName).newInstance();
                addValve(valve);
            }
            catch (Exception e)
            {
                //TODO handle Exception
                e.printStackTrace();
            }
        }
    }

    /**
     * Method process() in class WaterviewPipeline
     * 
     * @param runtime
     * @throws Exception
     */
    public void process(UIRuntime runtime) throws Exception
    {

    }
}