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
package com.cyclopsgroup.courselist.ui.action;

import com.cyclopsgroup.courselist.Course;
import com.cyclopsgroup.courselist.CoursePersistenceManager;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Save course change
 */
public class SaveCourseAction extends BaseServiceable implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute(RuntimeData data, ActionContext context)
            throws Exception
    {
        String number = data.getParams().getString("course_number");
        CoursePersistenceManager cpm = (CoursePersistenceManager) lookupComponent(CoursePersistenceManager.ROLE);
        Course course = cpm.findByNumber(number);

        course.setTitle(data.getParams().getString("course_title"));
        course.setDescription(data.getParams().getString("description"));
        course.setPrerequisite(data.getParams().getString("pre_requisite"));
        course.setCoRequisite(data.getParams().getString("co_requisite"));

        cpm.save(course);
    }
}
