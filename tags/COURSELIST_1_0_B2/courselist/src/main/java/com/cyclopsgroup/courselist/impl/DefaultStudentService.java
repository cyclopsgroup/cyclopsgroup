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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.CourseStatus;
import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.User;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Defualt implementation of StudentService
 */
public class DefaultStudentService
    extends AbstractHibernateEnabled
    implements StudentService, Serviceable
{
    private SecurityService security;

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
     * Overwrite or implement method dropCourse()
     *
     * @see com.cyclopsgroup.courselist.StudentService#dropCourse(java.lang.String, java.lang.String)
     */
    public void dropCourse( String studentId, String courseId )
        throws Exception
    {
        Session s = getHibernateSession();
        Criteria criteria = s.createCriteria( StudentCourse.class );
        criteria.add( Expression.eq( "studentId", studentId ) ).add( Expression.eq( "courseId", courseId ) );
        for ( Iterator i = criteria.list().iterator(); i.hasNext(); )
        {
            StudentCourse sc = (StudentCourse) i.next();
            if ( sc.getStatus() != CourseStatus.DROPPED )
            {
                sc.setStatus( CourseStatus.DROPPED );
                s.update( sc );
            }
        }
    }

    /**
     * Overwrite or implement method findAllByStudent()
     *
     * @see com.cyclopsgroup.courselist.StudentService#findAllByStudent(java.lang.String)
     */
    public List findAllByStudent( String studentId )
        throws Exception
    {
        return getHibernateSession().createCriteria( StudentCourse.class )
            .add( Expression.eq( "studentId", studentId ) ).list();
    }

    /**
     * Overwrite or implement method findByCourse()
     *
     * @see com.cyclopsgroup.courselist.StudentService#findByCourse(java.lang.String)
     */
    public List findByCourse( String courseId )
        throws Exception
    {
        return getHibernateSession().createCriteria( StudentCourse.class ).add( Expression.eq( "courseId", courseId ) )
            .add( Expression.eq( "relationType", CourseStatus.TAKING.getName() ) ).list();
    }

    /**
     * Overwrite or implement method getCourse()
     *
     * @see com.cyclopsgroup.courselist.StudentService#findByStudent(java.lang.String)
     */
    public List findByStudent( String studentId )
        throws Exception
    {
        return getHibernateSession().createCriteria( StudentCourse.class )
            .add( Expression.eq( "studentId", studentId ) ).add(
                                                                 Expression.eq( "relationType", CourseStatus.TAKING
                                                                     .getName() ) ).list();
    }

    /**
     * Overwrite or implement method getAllStudents()
     *
     * @see com.cyclopsgroup.courselist.StudentService#getAllStudents()
     */
    public List getAllStudents()
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( User.class );
        criteria.add( Expression.eq( "isDisabled", Boolean.FALSE ) );
        List users = criteria.list();
        List ret = new ArrayList();
        for ( Iterator i = users.iterator(); i.hasNext(); )
        {
            User user = (User) i.next();
            RuntimeUser ru = (RuntimeUser) security.getUser( user.getName() );
            if ( ru.hasRole( ROLE_STUDENT ) )
            {
                ret.add( user );
            }
        }
        return ret;
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
     * Overwrite or implement method service()
     *
     * @see com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        super.service( serviceManager );
        security = (SecurityService) serviceManager.lookup( SecurityService.ROLE );
    }
}
