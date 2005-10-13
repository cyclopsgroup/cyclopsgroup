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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled;

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
     * Overwrite or implement method getAllCourses()
     *
     * @see com.cyclopsgroup.courselist.CourseListService#getAllCourses()
     */
    public List getAllCourses()
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( Course.class );
        return criteria.add( Expression.eq( "isDisabled", Boolean.FALSE ) ).list();
    }
}