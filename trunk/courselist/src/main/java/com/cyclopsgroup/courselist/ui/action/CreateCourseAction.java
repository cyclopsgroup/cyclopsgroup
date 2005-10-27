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

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Course;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Parameters;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to create course
 */
public class CreateCourseAction
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
        Parameters params = data.getParameters();
        String prefix = params.getString( "prefix" );
        String code = params.getString( "courseCode" );
        CourseListService cl = (CourseListService) lookup( CourseListService.ROLE );
        if ( cl.findByCode( prefix, code ) != null )
        {
            context.error( "courseCode", "This course already exists" );
        }

        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        Course course = (Course) persist.create( Course.class );
        TypeUtils.getBeanUtils().populate( course, params.toProperties() );

        persist.saveNew( course );
        context.addMessage( "New course " + prefix + "/" + code + " is created" );
    }
}
