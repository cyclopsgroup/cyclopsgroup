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
package com.cyclopsgroup.lloyd;

/**
 * Facade interface for security manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface SecurityManager
{
    /** Role name in container */
    String ROLE = SecurityManager.class.getName();

    /**
     * Check if given asset is acceptable by the runtime user
     *
     * @param runtimeUser Runtime user object
     * @param asset Asset
     * @return
     */
    boolean accept(RuntimeUser runtimeUser, Asset asset);

    /**
     * Get runtime user object
     *
     * @param runtimeId Runtime user id
     * @return Runtime user object
     */
    RuntimeUser getRuntimeUser(String runtimeId);

    /**
     * Get user manager interface
     *
     * @return User manager instanceF
     */
    UserPersistenceManager getUserManager();

    /**
     * Start runtime user with given user
     *
     * @param user Given user entity
     * @param runtimeId Given runtime id
     * @return Rutime user model
     */
    RuntimeUser startRuntimeUser(User user, String runtimeId);
}