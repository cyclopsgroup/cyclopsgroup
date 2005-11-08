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
package com.cyclopsgroup.courselist.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.CourseStatus;
import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Defualt implementation of StudentService
 */
public class DefaultStudentService
    extends AbstractHibernateEnabled
    implements StudentService
{
    private void addPrerequisites( HashSet ids, Course course )
    {
        for ( Iterator i = course.getPrerequisites().iterator(); i.hasNext(); )
        {
            Course prereq = (Course) i.next();
            if ( !ids.contains( prereq.getId() ) )
            {
                ids.add( prereq.getId() );
                addPrerequisites( ids, prereq );
            }
        }
    }

    /**
     * Overwrite or implement method getStatus()
     *
     * @see com.cyclopsgroup.courselist.StudentService#getStatus(java.lang.String, java.lang.String)
     */
    public CourseStatus getStatus( String studentId, String courseId )
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( StudentCourse.class );
        criteria.add( Expression.eq( "studentId", studentId ) ).add( Expression.eq( "courseId", courseId ) );
        List relations = criteria.list();
        if ( relations.isEmpty() )
        {
            return CourseStatus.NONE;
        }
        for ( Iterator i = relations.iterator(); i.hasNext(); )
        {
            StudentCourse sc = (StudentCourse) i.next();
            if ( sc.getStatus() != CourseStatus.DROPPED )
            {
                return sc.getStatus();
            }
        }
        return CourseStatus.DROPPED;
    }

    /**
     * Overwrite or implement method isReadyFor()
     *
     * @see com.cyclopsgroup.courselist.StudentService#isReadyFor(java.lang.String, com.cyclopsgroup.courselist.entity.Course)
     */
    public boolean isReadyFor( String studentId, Course course )
        throws Exception
    {
        HashSet prereqIds = new HashSet();
        addPrerequisites( prereqIds, course );
        if ( prereqIds.isEmpty() )
        {
            return true;
        }

        for ( Iterator iter = prereqIds.iterator(); iter.hasNext(); )
        {
            String prereqId = (String) iter.next();
            if ( getStatus( studentId, prereqId ) != CourseStatus.FINISHED )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Overwrite or implement method addCourse()
     *
     * @see com.cyclopsgroup.courselist.StudentService#addCourse(java.lang.String, java.lang.String)
     */
    public void addCourse( String studentId, String courseId )
        throws Exception
    {
        StudentCourse sc = new StudentCourse();
        sc.setStudentId( studentId );
        sc.setCourseId( courseId );
        sc.setStatus( CourseStatus.TAKING );
        getHibernateSession().save( sc );
    }
}
