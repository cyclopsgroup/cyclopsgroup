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
package com.cyclopsgroup.courselist;

import com.cyclopsgroup.courselist.entity.CourseStatus;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Test case for student service
 */
public class StudentServiceTest
    extends TestCaseBase
{
    /**
     * Test if service is there
     *
     * @throws Exception
     */
    public void testExisting()
        throws Exception
    {
        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        assertNotNull( ss );
    }

    /**
     * Test student
     *
     * @throws Exception Throw it out
     */
    public void testStudent()
        throws Exception
    {
        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        assertEquals( CourseStatus.NONE, ss.getStatus( "aaa", "bbbb" ) );
    }
}
