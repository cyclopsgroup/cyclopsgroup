/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.lloyd.spi;

import java.util.HashMap;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.lloyd.AccessControlList;
import com.cyclopsgroup.lloyd.RuntimeUser;
import com.cyclopsgroup.lloyd.User;

/**
 * Default runtime user implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultRuntimeUser implements RuntimeUser
{

    private AccessControlList accessControlList;

    private boolean anonymous = false;

    private Context context;

    private Context dummyContext;

    private Context globalContext;

    private String id;

    private User user;

    /**
     * Constructor of default runtime user
     *
     * @param user User object
     * @param id Runtime id of this user
     * @param accessControlList acl of this user
     * @param globalContext Global context object
     */
    public DefaultRuntimeUser(User user, String id,
            AccessControlList accessControlList, Context globalContext)
    {
        this.user = user;
        this.id = id;
        this.globalContext = globalContext;
        this.dummyContext = new DefaultContext(new HashMap(), globalContext);
        this.context = new DefaultContext(new HashMap(), globalContext);
        this.accessControlList = accessControlList;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.RuntimeUser#getAccessControlList()
     */
    public AccessControlList getAccessControlList()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.RuntimeUser#getContext()
     */
    public Context getContext()
    {
        return isAnonymous() ? dummyContext : context;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.RuntimeUser#getId()
     */
    public String getId()
    {
        return id;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.RuntimeUser#getUser()
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.RuntimeUser#isAnonymous()
     */
    public boolean isAnonymous()
    {
        return anonymous;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param anonymous
     */
    public void setAnonymous(boolean anonymous)
    {
        this.anonymous = anonymous;
    }
}
