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
package com.cyclopsgroup.courselist.ui.action.teacher;

import java.util.Iterator;
import java.util.List;

import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.courselist.entity.CourseStatus;
import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to finish a course
 */
public class FinishCourseAction
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
        String courseId = data.getParameters().getString( "course_id" );
        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        PersistenceManager pm = (PersistenceManager) lookup( PersistenceManager.ROLE );
        List scs = ss.findByCourse( courseId );
        for ( Iterator i = scs.iterator(); i.hasNext(); )
        {
            StudentCourse sc = (StudentCourse) i.next();
            sc.setStatus( CourseStatus.FINISHED );
            pm.update( sc );
        }
        context.addMessage( "Course is finished" );
    }
}