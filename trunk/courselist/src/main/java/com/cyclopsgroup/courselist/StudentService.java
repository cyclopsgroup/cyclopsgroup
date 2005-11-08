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
import com.cyclopsgroup.courselist.entity.CourseStatus;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Student related service
 */
public interface StudentService
{
    /** Role of this service */
    String ROLE = StudentService.class.getName();

    /**
     * Get status of course
     *
     * @param studentId Student id
     * @param courseId Course id
     * @return Course status object
     * @throws Exception throw it out
     */
    CourseStatus getStatus( String studentId, String courseId )
        throws Exception;

    /**
     * Is student ready for taking a course
     *
     * @param studentId Student id
     * @param course Course id
     * @return True if all prerequisites are already taken
     * @throws Exception Throw it out
     */
    boolean isReadyFor( String studentId, Course course )
        throws Exception;

    /**
     * Add course for a student
     *
     * @param studentId Student id
     * @param courseId Course id
     * @throws Exception Throw it out
     */
    void addCourse( String studentId, String courseId )
        throws Exception;
}
