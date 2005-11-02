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
package com.cyclopsgroup.waterview.spi;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.jelly.JellyEngine;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Test case for look and feel
 */
public class LookAndFeelServiceTest
    extends PlexusTestCase
{
    public void testTheme()
        throws Exception
    {
        lookup( JellyEngine.ROLE );
        LookAndFeelService laf = (LookAndFeelService) lookup( LookAndFeelService.ROLE );
        Theme theme = laf.getDefaultTheme();
        assertNotNull( theme );
        Layout layout = theme.getLayout( Theme.LAYOUT_FOR_DEFAULT );
        assertNotNull( layout );
    }
}
