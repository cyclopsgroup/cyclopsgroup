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
package com.cyclopsgroup.courselist;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Persistence manager for course
 */
public interface CoursePersistenceManager
{
    /** Role name of cmoponent */
    String ROLE = CoursePersistenceManager.class.getName();

    /**
     * Delete course
     *
     * @param course Course to delete
     */
    void delete(Course course);

    /**
     * Delete course by number
     *
     * @param number Course number
     */
    void delete(String number);

    /**
     * Find course with given filter
     *
     * @param filter Course filter
     * @return Array of courses
     */
    Course[] find(CourseFilter filter);

    /**
     * Find course by number
     *
     * @param number Course number
     * @return Course object
     */
    Course findByNumber(String number);

    /**
     * Save course chagne
     *
     * @param course Course object
     */
    void save(Course course);
}