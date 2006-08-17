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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.core.ParseURLValve.Part;
import com.cyclopsgroup.waterview.core.ParseURLValve.PartIterator;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.test.MockRunData;
import com.cyclopsgroup.waterview.test.WaterviewTestCase;

/**
 * Test case for ParseURLValve
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValveTest
    extends WaterviewTestCase
{
    /**
     * Test invocation
     *
     * @throws Exception
     */
    public void testInvoke()
        throws Exception
    {
        lookup( JellyEngine.ROLE );
        MockRunData data = createRunData();
        data.setRequestPath( "/!do!/aaa/!do!/bbb/BAction/!do!/ccc/!show!/ddd.jelly" );
        ParseURLValve v = (ParseURLValve) lookup( ParseURLValve.class.getName() );
        v.invoke( data, new PipelineContext()
        {

            public void invokeNextValve( RunDataSpi data )
                throws Exception
            {
                //do nothing
            }
        } );
        //assertEquals("/ddd.jelly", runtime.getPage());

        List<Path> actions = data.getPaths( Link.INSTRUCTION_DO );

        assertEquals( 3, actions.size() );
        assertEquals( "/aaa", actions.get( 0 ).getPath() );
        assertEquals( "/bbb/BAction", actions.get( 1 ).getPath() );
        assertEquals( "/ccc", actions.get( 2 ).getPath() );
    }

    public void testSomethingStupid()
    {
        String path = "/!do!/aaa/!do!/bbb/BAction/!do!/ccc/!show!/ddd.jelly";

        PartIterator pi = new PartIterator( path );
        List<Part> result = new ArrayList<Part>();
        CollectionUtils.addAll( result, pi );

        assertEquals( 4, result.size() );
        assertEquals( "do:/aaa", result.get( 0 ).toString() );
        assertEquals( "do:/bbb/BAction", result.get( 1 ).toString() );
        assertEquals( "do:/ccc", result.get( 2 ).toString() );
        assertEquals( "show:/ddd.jelly", result.get( 3 ).toString() );
    }
}
