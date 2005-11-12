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
package com.cyclopsgroup.courselist.ui.view.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.Teacher;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.SelectOption;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Module to edit course
 */
public class EditCourse
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
        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        Course course = (Course) persist.load( Course.class, data.getParameters().getString( "course_id" ) );
        context.put( "course", course );

        CourseListService cls = (CourseListService) lookup( CourseListService.ROLE );
        List teachers = cls.getAllTeachers();

        List teacherItems = new ArrayList();
        for ( Iterator i = teachers.iterator(); i.hasNext(); )
        {
            final Teacher teacher = (Teacher) i.next();
            teacherItems.add( new SelectOption()
            {
                public String getName()
                {
                    return teacher.getUserId();
                }

                public String getTitle()
                {
                    return teacher.getUser().getDisplayName();
                }
            } );
        }
        context.put( "teachers", teacherItems );
    }
}
