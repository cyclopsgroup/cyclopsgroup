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

import java.util.Iterator;
import java.util.List;

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
    };
}