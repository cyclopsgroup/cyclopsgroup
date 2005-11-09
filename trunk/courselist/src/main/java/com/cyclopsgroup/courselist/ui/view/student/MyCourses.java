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
package com.cyclopsgroup.courselist.ui.view.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.courselist.entity.CourseStatus;
import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * My courses
 */
public class MyCourses
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
        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        RuntimeUser user = RuntimeUser.getInstance( data );
        context.put( "student", user );
        List studentCourses = ss.getCourses( user.getId() );
        List courses = new ArrayList();
        for ( Iterator i = studentCourses.iterator(); i.hasNext(); )
        {
            StudentCourse sc = (StudentCourse) i.next();
            if ( sc.getStatus() == CourseStatus.TAKING )
            {
                courses.add( sc.getCourse() );
            }
        }
        context.put( "currentCourses", courses );
    }
}
