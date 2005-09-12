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
package com.cyclopsgroup.tornado.security.impl;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.security.Role;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.User;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultRuntimeUser
    extends RuntimeUser
{
    private String id;

    private String name;

    private String emailAddress;

    private String displayName;

    private boolean guest;

    private HashSet roleNames = new HashSet();

    /**
     * @param role Role object
     */
    public void addRole( Role role )
    {
        if ( roleNames.contains( role.getName() ) )
        {
            return;
        }
        roleNames.add( role.getName() );
        for ( Iterator i = role.getDependencies().iterator(); i.hasNext(); )
        {
            Role dependency = (Role) i.next();
            addRole( dependency );
        }
    }

    /**
     * Constructor for class RuntimeUserBase
     *
     * @param user User object
     */
    protected DefaultRuntimeUser( User user )
    {
        id = user.getId();
        name = user.getName();
        emailAddress = user.getEmail();
        StringBuffer sb = new StringBuffer( user.getFirstName() );
        if ( StringUtils.isNotEmpty( user.getMiddleName() ) )
        {
            sb.append( " " ).append( user.getMiddleName() );
        }
        sb.append( " " ).append( user.getLastName() );
        displayName = sb.toString();
        guest = user.getName().equals( SecurityService.USER_GUEST );
    }

    /**
     * Override method getId in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getId()
     */
    public String getId()
    {
        return id;
    }

    /**
     * Override method getName in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Override method getDisplayName in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getDisplayName()
     */
    public String getDisplayName()
    {
        return displayName;
    }

    /**
     * Override method isGuest in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#isGuest()
     */
    public boolean isGuest()
    {
        return guest;
    }

    /**
     * Override method getEmailAddress in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getEmailAddress()
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * Override method hasRole in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#hasRole(java.lang.String)
     */
    public boolean hasRole( String roleName )
    {
        return roleNames.contains( roleName );
    }

    /**
     * Override method hasPermission in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#hasPermission(java.lang.Object)
     */
    public boolean hasPermission( Object permission )
    {
        // TODO Auto-generated method stub
        return false;
    }
}
