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
package com.cyclopsgroup.waterview.navigator.impl;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.navigator.NavigatorService;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Test case for default navigator home
 */
public class DefaultNavigatorHomeTest
    extends PlexusTestCase
{
    /**
     * Test getPath();
     *
     * @throws Exception
     */
    public void testGetPath()
        throws Exception
    {
        NavigatorService nav = (NavigatorService) lookup( NavigatorService.ROLE );
        NavigatorNode node = nav.getNodeByPage( "/waterview/system/status/SessionDump.jelly" );
        NavigatorNode[] path = node.getParentNodes();
        assertEquals( 3, path.length );
    }
}
