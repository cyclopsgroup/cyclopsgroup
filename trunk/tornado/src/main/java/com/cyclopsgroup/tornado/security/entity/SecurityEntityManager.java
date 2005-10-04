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
package com.cyclopsgroup.tornado.security.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.AbstractHibernateEnabled;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Security entity manager
 */
public class SecurityEntityManager
    extends AbstractHibernateEnabled
{
    /** Role name */
    public static final String ROLE = SecurityEntityManager.class.getName();

    /**
     * Find active group by its name
     *
     * @param groupName Group name
     * @return Group object
     * @throws Exception Throw it outs
     */
    public Group findGroupByName( String groupName )
        throws Exception
    {
        Session s = getHibernateSession();
        List rs = s.createCriteria( Group.class ).add( Expression.eq( "name", groupName ) )
            .add( Expression.eq( "isDisabled", Boolean.FALSE ) ).setMaxResults( 1 ).list();
        return rs.isEmpty() ? null : (Group) rs.get( 0 );
    }

    /**
     * Find role by name
     *
     * @param roleName Role name
     * @return Role object
     * @throws Exception Throw it out
     */
    public Role findRoleByName( String roleName )
        throws Exception
    {
        Session s = getHibernateSession();
        List list = s.createCriteria( Role.class ).add( Expression.eq( "name", roleName ) )
            .add( Expression.eq( "isDisabled", Boolean.FALSE ) ).setMaxResults( 1 ).list();
        return list.isEmpty() ? null : (Role) list.get( 0 );
    }

    /**
     * Find role permissions by given role ids
     *
     * @param roleIds Role ids
     * @return List of role permissions
     * @throws Exception Throw it out
     */
    public List findRolePermissionsByRoles( String[] roleIds )
        throws Exception
    {
        return getHibernateSession().createCriteria( RolePermission.class ).add( Expression.in( "roleId", roleIds ) )
            .list();
    }

    /**
     * Find roles connected to an user
     *
     * @param groupId Gropu Id
     * @return List of roles
     * @throws Exception Throw it out
     */
    public List findRolesByGroup( String groupId )
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( GroupRole.class );
        List groupRoles = criteria.add( Expression.eq( "groupId", groupId ) ).list();
        HashMap roles = new HashMap();
        for ( Iterator i = groupRoles.iterator(); i.hasNext(); )
        {
            GroupRole gr = (GroupRole) i.next();
            roles.put( gr.getRoleId(), gr.getRole() );
        }
        return new ArrayList( roles.values() );
    }

    /**
     * Find roles connected to an user
     *
     * @param userId User id
     * @return List of roles
     * @throws Exception Throw it out
     */
    public List findRolesByUser( String userId )
        throws Exception
    {
        Criteria criteria = getHibernateSession().createCriteria( UserRole.class );
        List userRoles = criteria.add( Expression.eq( "userId", userId ) ).list();
        HashMap roles = new HashMap();
        for ( Iterator i = userRoles.iterator(); i.hasNext(); )
        {
            UserRole ur = (UserRole) i.next();
            roles.put( ur.getRoleId(), ur.getRole() );
        }
        return new ArrayList( roles.values() );
    }

    /**
     * Find active user by its name
     *
     * @param name User name
     * @return User object
     * @throws Exception Throw it out
     */
    public User findUserByName( String name )
        throws Exception
    {
        Session s = getHibernateSession();
        List rs = s.createCriteria( User.class ).add( Expression.eq( "isDisabled", Boolean.FALSE ) )
            .add( Expression.eq( "name", name ) ).setMaxResults( 1 ).list();
        return rs.isEmpty() ? null : (User) rs.get( 0 );
    };

    /**
     * Get all active groups
     *
     * @return List of all groups
     * @throws Exception Throw it out
     */
    public List getAllGroups()
        throws Exception
    {
        return getHibernateSession().createCriteria( Group.class ).add( Expression.eq( "isDisabled", Boolean.FALSE ) )
            .list();
    }

    /**
     * Join gropu
     *
     * @param userId User id
     * @param groupId Group id
     * @throws Exception Throw it out
     */
    public void joinGroup( String userId, String groupId )
        throws Exception
    {
        UserGroup ug = new UserGroup();
        ug.setUserId( userId );
        ug.setGroupId( groupId );
        getHibernateSession().save( ug );
    }

    /**
     * User leave group
     *
     * @param userId User id
     * @param groupIds Group ids
     * @throws Exception Throw it out
     */
    public void leaveGroups( String userId, String[] groupIds )
        throws Exception
    {
        Session s = getHibernateSession();
        List ugs = s.createCriteria( UserGroup.class ).add( Expression.eq( "userId", userId ) )
            .add( Expression.in( "groupId", groupIds ) ).list();
        for ( Iterator i = ugs.iterator(); i.hasNext(); )
        {
            UserGroup ug = (UserGroup) i.next();
            s.delete( ug );
        }
    }
}