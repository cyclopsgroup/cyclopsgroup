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
package com.cyclopsgroup.tornado.components.security.entity;

import java.util.Set;

import com.cyclopsgroup.tornado.core.security.Group;
import com.cyclopsgroup.tornado.core.security.Permission;
import com.cyclopsgroup.tornado.core.security.Role;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tornado implemented group model
 */
public class TornadoGroup extends BaseSecurityEntity implements Group
{
    private Set authorizedPermissions;

    private Set authorizedRoles;

    private Set parentGroups;

    /**
     * @see com.cyclopsgroup.tornado.core.security.Group#getAuthorizedPermissions()
     */
    public Permission[] getAuthorizedPermissions() {
        return (Permission[]) authorizedPermissions
                .toArray(Permission.EMPTY_ARRAY);
    }

    /**
     * @see com.cyclopsgroup.tornado.core.security.Group#getAuthorizedRoles()
     */
    public Role[] getAuthorizedRoles() {
        return (Role[]) authorizedRoles.toArray(Role.EMPTY_ARRAY);
    }

    /**
     * @see com.cyclopsgroup.tornado.core.security.Group#getParentGroups()
     */
    public Group[] getParentGroups() {
        return (Group[]) parentGroups.toArray(Group.EMPTY_ARRAY);
    }
}
