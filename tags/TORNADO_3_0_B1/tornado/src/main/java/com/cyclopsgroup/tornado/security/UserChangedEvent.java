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
package com.cyclopsgroup.tornado.security;

import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * User login event
 */
public class UserChangedEvent
{
    private RuntimeData data;

    private RuntimeUser user;

    /**
     * Constructor for class UserLoginEvent
     *
     * @param user Logged in user
     * @param data Runtime data
     */
    public UserChangedEvent( RuntimeUser user, RuntimeData data )
    {
        this.user = user;
        this.data = data;
    }

    /**
     * Getter method for property data
     *
     * @return Returns the data.
     */
    public RuntimeData getRuntimeData()
    {
        return data;
    }

    /**
     * Getter method for property user
     *
     * @return Returns the user.
     */
    public RuntimeUser getUser()
    {
        return user;
    }
}
