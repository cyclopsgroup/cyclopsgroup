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
package com.cyclopsgroup.courselist.ui.action.course;

import java.util.Iterator;

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.courselist.entity.CoursePrerequisite;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Action to add prerequisite course
 */
public class AddPrerequisiteAction
    extends BaseServiceable
    implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        CourseListService cls = (CourseListService) lookup( CourseListService.ROLE );
        Course prerequisite = cls.findByCode( Course.PREFIX, data.getParameters().getString( "course_number" ) );
        if ( prerequisite == null )
        {
            context.error( "course_number", "Course is not found" );
            return;
        }

        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        String courseId = data.getParameters().getString( "course_id" );
        Course course = (Course) persist.load( Course.class, courseId );
        for ( Iterator i = course.getPrerequisites().iterator(); i.hasNext(); )
        {
            Course pre = (Course) i.next();
            if ( pre.getId().equals( prerequisite.getId() ) )
            {
                context.error( "course_number", "Course is already a prerequisite" );
                return;
            }
        }

        CoursePrerequisite cp = new CoursePrerequisite();
        cp.setCourseId( courseId );
        cp.setPrerequisiteId( prerequisite.getId() );
        persist.saveNew( cp );
    }
}
