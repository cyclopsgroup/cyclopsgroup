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
package com.cyclopsgroup.courselist.ui;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Test case
 */
public class DefaultLayoutTest
    extends PlexusTestCase
{
    /**
     * Test case to find layout
     *
     * @throws Exception
     */
    public void testLayout()
        throws Exception
    {
        lookup( JellyEngine.ROLE );
        LookAndFeelService tm = (LookAndFeelService) lookup( LookAndFeelService.ROLE );
        Theme t = tm.getDefaultTheme();
        assertNotNull( t );
        Layout layout = t.getLayout( "layout.default" );

        assertNotNull( layout );
    }
}
