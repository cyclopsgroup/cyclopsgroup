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
package com.cyclopsgroup.courselist.ui.action.course;

import com.cyclopsgroup.courselist.CourseListService;
import com.cyclopsgroup.courselist.entity.Teacher;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.Role;
import com.cyclopsgroup.tornado.security.entity.SecurityEntityManager;
import com.cyclopsgroup.tornado.security.entity.UserRole;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to add a teacher
 */
public class AddTeacherAction
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
        SecurityService security = (SecurityService) lookup( SecurityService.ROLE );
        String userName = data.getParameters().getString( "user_name" );
        RuntimeUser user = (RuntimeUser) security.getUser( userName );
        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        Teacher teacher = (Teacher) persist.create( Teacher.class );
        teacher.setUserId( user.getId() );
        persist.saveNew( teacher );
        if ( !user.hasRole( CourseListService.ROLE_TEACHER ) )
        {
            SecurityEntityManager sem = (SecurityEntityManager) lookup( SecurityEntityManager.ROLE );
            Role role = sem.findRoleByName( CourseListService.ROLE_TEACHER );
            UserRole ur = (UserRole) persist.create( UserRole.class );
            ur.setUserId( user.getId() );
            ur.setRoleId( role.getId() );
            persist.saveNew( ur );
            security.refreshUser( user.getName() );
        }
        context.addMessage( "User " + userName + " is added as a teacher" );
    }
}
