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
package com.cyclopsgroup.courselist.ui.view.teacher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.SelectOption;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Module for course detail view
 */
public class MyCourseDetail
    extends BaseServiceable
    implements Module
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        String courseId = data.getParameters().getString( "course_id" );

        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        Course course = (Course) persist.load( Course.class, courseId );
        context.put( "course", course );

        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        List studentCourses = ss.findByCourse( courseId );
        context.put( "studentCourses", studentCourses );

        HashSet existingStudentIds = new HashSet();
        for ( Iterator i = studentCourses.iterator(); i.hasNext(); )
        {
            StudentCourse sc = (StudentCourse) i.next();
            existingStudentIds.add( sc.getStudentId() );
        }

        List allStudents = ss.getAllStudents();
        TreeMap students = new TreeMap();
        for ( Iterator i = allStudents.iterator(); i.hasNext(); )
        {
            final User user = (User) i.next();
            if ( !existingStudentIds.contains( user.getId() ) )
            {
                students.put( user.getName(), new SelectOption()
                {
                    public String getName()
                    {
                        return user.getName();
                    }

                    public String getTitle()
                    {
                        return user.getName() + "(" + user.getDisplayName() + ")";
                    }
                } );
            }
        }
        context.put( "students", students.values() );
    }
}
