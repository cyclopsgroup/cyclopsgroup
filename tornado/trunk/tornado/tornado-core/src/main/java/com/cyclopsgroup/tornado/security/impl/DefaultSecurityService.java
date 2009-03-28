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

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.avalon.framework.activity.Startable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.tornado.security.CreateUserEvent;
import com.cyclopsgroup.tornado.security.NoSuchUserException;
import com.cyclopsgroup.tornado.security.Permission;
import com.cyclopsgroup.tornado.security.PermissionType;
import com.cyclopsgroup.tornado.security.RuntimeUserAPI;
import com.cyclopsgroup.tornado.security.SecurityListener;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.Group;
import com.cyclopsgroup.tornado.security.entity.Role;
import com.cyclopsgroup.tornado.security.entity.RolePermission;
import com.cyclopsgroup.tornado.security.entity.SecurityEntityManager;
import com.cyclopsgroup.tornado.security.entity.User;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultSecurityService
    extends AbstractLogEnabled
    implements SecurityService, Serviceable, Startable
{
    private Thread checkTimeoutThread;

    private SecurityEntityManager sem;

    private Vector listeners = new Vector();

    private Hashtable runtimeUsers = new Hashtable();

    private Hashtable userEntries = new Hashtable();

    /**
     * Override method addListener in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#addListener(com.cyclopsgroup.tornado.security.SecurityListener)
     */
    public void addListener( SecurityListener listener )
    {
        listeners.add( listener );
    }

    private void checkTimeout()
    {
        for ( Iterator i = userEntries.keySet().iterator(); i.hasNext(); )
        {
            String sessionId = (String) i.next();
            UserEntry ue = (UserEntry) userEntries.get( sessionId );
            if ( ue.isExpired() )
            {
                userEntries.remove( sessionId );
            }
        }
    }

    /**
     * @param userName Name of user
     * @return Runtime user instance
     * @throws Exception Throw it out
     */
    protected RuntimeUserAPI doLoadUser( String userName )
        throws Exception
    {
        User userModel = sem.findUserByName( userName );
        if ( userModel == null )
        {
            throw new NoSuchUserException( userName );
        }
        DefaultRuntimeUser user = new DefaultRuntimeUser( userModel );

        List userRoles = sem.findRolesByUser( userModel.getId() );
        for ( Iterator i = userRoles.iterator(); i.hasNext(); )
        {
            user.addRole( (Role) i.next() );
        }
        user.addRole( sem.findRoleByName( ROLE_GUEST ) );
        if ( !userName.equals( USER_GUEST ) )
        {
            user.addRole( sem.findRoleByName( ROLE_USER ) );
        }

        for ( Iterator i = userModel.getGroups().iterator(); i.hasNext(); )
        {
            Group group = (Group) i.next();
            List groupRoles = sem.findRolesByGroup( group.getId() );
            for ( Iterator j = groupRoles.iterator(); j.hasNext(); )
            {
                user.addRole( (Role) j.next() );
            }
            if ( group.getName().equals( GROUP_ADMINS ) )
            {
                user.addRole( sem.findRoleByName( ROLE_ADMIN ) );
            }
        }
        String[] roleIdArray = (String[]) user.getRoleIds().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
        List rps = sem.findRolePermissionsByRoles( roleIdArray );
        for ( Iterator i = rps.iterator(); i.hasNext(); )
        {
            RolePermission rp = (RolePermission) i.next();
            try
            {
                PermissionType pt = (PermissionType) Class.forName( rp.getPermissionType() ).newInstance();
                Permission p = pt.createPermission( rp.getPermission() );
                user.addPermission( pt, p );
            }
            catch ( Exception e )
            {
                getLogger().warn( "Permission is not valid " + rp.getPermissionType() + "|" + rp.getPermission() );
            }
        }

        return user;
    }

    /**
     * Override method getGuest in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#getGuestUser()
     */
    public RuntimeUserAPI getGuestUser()
        throws Exception
    {
        return getUser( USER_GUEST );
    }

    /**
     * Override method getUser in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#getUser(java.lang.String)
     */
    public synchronized RuntimeUserAPI getUser( String userName )
        throws Exception
    {
        RuntimeUserAPI user = (RuntimeUserAPI) runtimeUsers.get( userName );
        if ( user == null )
        {
            user = doLoadUser( userName );
            handleEvent( new CreateUserEvent( user ) );
            runtimeUsers.put( userName, user );
        }
        return user;
    }

    /**
     * Override method getUserBySessionId in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#getUserBySessionId(java.lang.String)
     */
    public RuntimeUserAPI getUserBySessionId( String sessionId )
        throws Exception
    {
        UserEntry ue = (UserEntry) userEntries.get( sessionId );
        if ( ue == null )
        {
            return getGuestUser();
        }
        ue.update();
        return getUser( ue.getUserName() );
    }

    /**
     * Override method handleEvent in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#handleEvent(java.lang.Object)
     */
    public void handleEvent( Object event )
    {
        if ( event == null )
        {
            return;
        }
        for ( Iterator i = listeners.iterator(); i.hasNext(); )
        {
            SecurityListener listener = (SecurityListener) i.next();
            try
            {
                listener.performAction( event );
            }
            catch ( Exception e )
            {
                getLogger().warn( "Event handling error", e );
            }
        }
    }

    /**
     * Override method login in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#login(java.lang.String, java.lang.String, long)
     */
    public RuntimeUserAPI login( String userName, String sessionId, long timeout )
        throws Exception
    {
        UserEntry ue = new UserEntry( userName, timeout );
        userEntries.put( sessionId, ue );
        return getUser( userName );
    }

    /**
     * Override method logout in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#logout(java.lang.String)
     */
    public void logout( String sessionId )
    {
        UserEntry ue = (UserEntry) userEntries.remove( sessionId );
        if ( ue.getUserName().equals( USER_GUEST ) )
        {
            return;
        }
        boolean hasUser = false;
        for ( Iterator i = userEntries.values().iterator(); i.hasNext(); )
        {
            UserEntry u = (UserEntry) i.next();
            if ( u.getUserName().equals( ue.getUserName() ) )
            {
                hasUser = true;
                break;
            }
        }
        if ( !hasUser )
        {
            runtimeUsers.remove( ue.getUserName() );
        }
    }

    /**
     * Override method refreshUser in class DefaultSecurityService
     *
     * @see com.cyclopsgroup.tornado.security.SecurityService#refreshUser(java.lang.String)
     */
    public void refreshUser( String userName )
    {
        runtimeUsers.remove( userName );
    }

    /**
     * Override method service in class DefaultSecurityService
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        sem = (SecurityEntityManager) serviceManager.lookup( SecurityEntityManager.ROLE );
    }

    /**
     * Override method start in class DefaultSecurityService
     *
     * @see org.apache.avalon.framework.activity.Startable#start()
     */
    public void start()
        throws Exception
    {
        checkTimeoutThread = new Thread()
        {
            public void run()
            {
                while ( true )
                {
                    try
                    {
                        Thread.sleep( 10000 );
                        checkTimeout();
                    }
                    catch ( InterruptedException e )
                    {
                        break;
                    }
                }
            }
        };
        checkTimeoutThread.setDaemon( true );
        checkTimeoutThread.setPriority( Thread.MIN_PRIORITY );
        checkTimeoutThread.start();
    }

    /**
     * Override method stop in class DefaultSecurityService
     *
     * @see org.apache.avalon.framework.activity.Startable#stop()
     */
    public void stop()
        throws Exception
    {
        checkTimeoutThread.interrupt();
        checkTimeoutThread = null;
    }
}