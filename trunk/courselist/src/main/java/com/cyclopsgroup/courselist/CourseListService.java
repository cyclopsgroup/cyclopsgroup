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

import java.util.List;

import com.cyclopsgroup.courselist.entity.Course;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Facade course list service
 */
public interface CourseListService
{
    /** Service role */
    String ROLE = CourseListService.class.getName();

    /**
     * Find a course by prefix and code
     *
     * @param prefix Course prefix
     * @param code Course code
     * @return Course object or null if not found
     * @throws Exception Throw it out
     */
    Course findByCode( String prefix, String code )
        throws Exception;

    /**
     * Find all courses
     *
     * @return List of all courses
     * @throws Exception Throw it out
     */
    List getAllCourses()
        throws Exception;
}
