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

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.StudentService;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Teacher's courses
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
        CourseListService cls = (CourseListService) lookup( CourseListService.ROLE );
        RuntimeUser user = RuntimeUser.getInstance( data );
        context.put( "teacher", user );
        context.put( "myCourses", cls.findByTeacher( user.getId() ) );

        StudentService ss = (StudentService) lookup( StudentService.ROLE );
        context.put( "studentService", ss );
    }
}
