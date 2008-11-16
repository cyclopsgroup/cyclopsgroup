/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.utils;

import java.io.StringWriter;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Test case for interpolation filter writer
 */
public class InterpolationFilterWriterTest
    extends TestCase
{
    /**
     * Test scenario 1
     *
     * @throws Exception
     */
    public void testScenario1()
        throws Exception
    {
        StringWriter s = new StringWriter();
        InterpolationFilterWriter writer = new InterpolationFilterWriter( s, '%' )
        {
            /**
             * Overwrite or implement method interpolate()
             *
             * @see com.cyclopsgroup.waterview.utils.InterpolationFilterWriter#interpolate(java.lang.String)
             */
            protected String interpolate( String name )
                throws Exception
            {
                return "[a]";
            }
        };
        writer.write( (int) '%' );
        writer.write( "aa" );
        writer.write( "%missing,haha, %xyz%100%abc," );
        System.out.println( s.toString() );
        assertEquals( "[a][a],haha, [a]%100[a],", s.toString() );
    }
}
