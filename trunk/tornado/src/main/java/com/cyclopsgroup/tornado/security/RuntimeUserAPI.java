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

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public interface RuntimeUserAPI
{
    /**
     * @return Get unique id
     */
    String getId();

    /**
     * @return Get user account name
     */
    String getName();

    /**
     * @return Get displayed name
     */
    String getDisplayName();

    /**
     * @return Is the user guest or not
     */
    boolean isGuest();

    /**
     * @return Get email address
     */
    String getEmailAddress();

    /**
     * @param roleName Role name to check
     * @return True if user has such a role
     */
    boolean hasRole( String roleName );

    /**
     * @param permission Permission object
     * @return True if user has such permission
     */
    boolean hasPermission( Object permission );
}
