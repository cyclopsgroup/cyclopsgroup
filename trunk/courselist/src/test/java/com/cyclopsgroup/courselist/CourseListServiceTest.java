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

import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.waterview.LargeList;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Test case for courselist service
 */
public class CourseListServiceTest
    extends TestCaseBase
{
    /**
     * Test method for findByCode method
     *
     * @throws Exception
     */
    public void testFindByCode()
        throws Exception
    {
        CourseListService cls = (CourseListService) lookup( CourseListService.ROLE );
        Course course = cls.findByCode( "CS", "5348" );
        assertNotNull( course );
        assertEquals( "Operating Systems Concepts", course.getName() );
    }

    /**
     * Test method for getAllCourses()
     *
     * @throws Exception
     */
    public void testGetAllCourse()
        throws Exception
    {
        CourseListService cls = (CourseListService) lookup( CourseListService.ROLE );
        LargeList courses = cls.getAllCourses();
        assertNotNull( courses );
    }
}
