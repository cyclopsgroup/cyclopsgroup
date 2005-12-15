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

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.CoursePrerequisite;
import com.cyclopsgroup.courselist.entity.Teacher;
import com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled;
import com.cyclopsgroup.tornado.hibernate.CriteriaLargeList;
import com.cyclopsgroup.waterview.LargeList;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of course list
 */
public class DefaultCourseListService
    extends AbstractHibernateEnabled
    implements CourseListService
{
    /**
     * Overwrite or implement method addPrerequisite()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#addPrerequisite(java.lang.String, java.lang.String)
     */
    public void addPrerequisite( String courseId, String prereqId )
        throws Exception
    {
        Session s = getHibernateSession();
        Criteria criteria = s.createCriteria( CoursePrerequisite.class );
        criteria.add( Expression.eq( "courseId", courseId ) ).add( Expression.eq( "prerequisiteId", prereqId ) );
        List rs = criteria.list();
        if ( rs.isEmpty() )
        {
            CoursePrerequisite cp = new CoursePrerequisite();
            cp.setCourseId( courseId );
            cp.setPrerequisiteId( prereqId );
            s.save( cp );
        }
    }

    /**
     * Overwrite or implement method deletePrerequisite()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#deletePrerequisite(java.lang.String, java.lang.String)
     */
    public void deletePrerequisite( String courseId, String prereqId )
        throws Exception
    {
        Session s = getHibernateSession();
        Criteria criteria = s.createCriteria( CoursePrerequisite.class );
        criteria.add( Expression.eq( "courseId", courseId ) ).add( Expression.eq( "prerequisiteId", prereqId ) );
        List rs = criteria.list();
        for ( Iterator iter = rs.iterator(); iter.hasNext(); )
        {
            CoursePrerequisite cp = (CoursePrerequisite) iter.next();
            s.delete( cp );
        }
    }

    /**
     * Overwrite or implement method findByCode()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#findByCode(java.lang.String, java.lang.String)
     */
    public Course findByCode( String prefix, String code )
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( Course.class );
        criteria.add( Expression.eq( "prefix", prefix ) ).add( Expression.eq( "courseCode", code ) )
            .add( Expression.eq( "isDisabled", Boolean.FALSE ) );
        List rs = criteria.setMaxResults( 1 ).list();
        return rs.isEmpty() ? null : (Course) rs.get( 0 );
    }

    /**
     * Overwrite or implement method findByTeacher()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#findByTeacher(java.lang.String)
     */
    public List findByTeacher( String teacherId )
        throws Exception
    {
        return getHibernateSession().createCriteria( Course.class ).add( Expression.eq( "teacherId", teacherId ) )
            .add( Expression.eq( "isDisabled", Boolean.FALSE ) ).list();
    }

    /**
     * Overwrite or implement method getAllCourses()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#getAllCourses()
     */
    public LargeList getAllCourses()
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( Course.class );
        criteria.add( Expression.eq( "isDisabled", Boolean.FALSE ) );
        return new CriteriaLargeList( criteria );
    }

    /**
     * Overwrite or implement method getAllTeachers()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#getAllTeachers()
     */
    public List getAllTeachers()
        throws Exception
    {
        return getHibernateSession().createCriteria( Teacher.class ).list();
    }
}
