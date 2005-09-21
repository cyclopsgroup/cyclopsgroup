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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.security.Asset;
import com.cyclopsgroup.tornado.security.Permission;
import com.cyclopsgroup.tornado.security.PermissionType;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.Role;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.MapValueParser;
import com.cyclopsgroup.waterview.ValueParser;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultRuntimeUser
    extends RuntimeUser
{
    private ValueParser attributes = new MapValueParser( new MultiHashMap() );

    private String displayName;

    private String emailAddress;

    private boolean guest;

    private String id;

    private String name;

    private MultiMap permissions = new MultiHashMap();

    private HashSet roleIds = new HashSet();

    private HashSet roleNames = new HashSet();

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
     * Add permission
     *
     * @param type Permisson type
     * @param p Permission
     */
    public void addPermission( PermissionType type, Permission p )
    {
        permissions.put( type.getClass(), p );
    }

    /**
     * @param role Role object
     */
    public void addRole( Role role )
    {
        if ( role == null || role.getIsDisabled() || roleNames.contains( role.getName() ) )
        {
            return;
        }
        roleNames.add( role.getName() );
        roleIds.add( role.getId() );
        for ( Iterator i = role.getDependencies().iterator(); i.hasNext(); )
        {
            Role dependency = (Role) i.next();
            addRole( dependency );
        }
    }

    /**
     * Overwrite or implement method getAttributes()
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getAttributes()
     */
    public ValueParser getAttributes()
    {
        return attributes;
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
     * Override method getEmailAddress in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#getEmailAddress()
     */
    public String getEmailAddress()
    {
        return emailAddress;
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

    Set getRoleIds()
    {
        return Collections.unmodifiableSet( roleIds );
    }

    /**
     * Override method hasPermission in class RuntimeUserBase
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#hasPermission(java.lang.Object)
     */
    public boolean hasPermission( Object permission )
    {
        return permissions.containsValue( permission );
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
     * Overwrite or implement method isAuthorized()
     *
     * @see com.cyclopsgroup.tornado.security.RuntimeUserAPI#isAuthorized(com.cyclopsgroup.tornado.security.Asset)
     */
    public boolean isAuthorized( Asset asset )
    {
        if ( hasRole( SecurityService.ROLE_ADMIN ) )
        {
            return true;
        }
        Collection ps = (Collection) permissions.get( asset.getPermissionType().getClass() );
        if ( ps == null )
        {
            return false;
        }
        for ( Iterator iter = ps.iterator(); iter.hasNext(); )
        {
            Permission p = (Permission) iter.next();
            if ( asset.authorize( p ) )
            {
                return true;
            }
        }
        return false;
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
}
