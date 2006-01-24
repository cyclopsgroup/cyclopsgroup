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

import com.cyclopsgroup.waterview.Attributes;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public interface RuntimeUserAPI
{
    /**
     * Get attributes
     *
     * @return User attributes
     */
    Attributes getAttributes();

    /**
     * @return Get displayed name
     */
    String getDisplayName();

    /**
     * @return Get email address
     */
    String getEmailAddress();

    /**
     * @return Get unique id
     */
    String getId();

    /**
     * @return Get user account name
     */
    String getName();

    /**
     * @param permission Permission object
     * @return True if user has such permission
     */
    boolean hasPermission(Object permission);

    /**
     * @param roleName Role name to check
     * @return True if user has such a role
     */
    boolean hasRole(String roleName);

    /**
     * Check if user is authorized to an asset
     *
     * @param asset Asset to check
     * @return True if it's authorized
     */
    boolean isAuthorized(Asset asset);

    /**
     * @return Is the user guest or not
     */
    boolean isGuest();
}
