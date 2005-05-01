/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
 * Component to check user only
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface UserAuthenticator
{

    /**
     * Comment for <code>RESULT_AUTHENTICATED</code>
     */
    int RESULT_AUTHENTICATED = 0;

    /**
     * Comment for <code>RESULT_INVALID_PASSWORD</code>
     */
    int RESULT_INVALID_PASSWORD = 2;

    /**
     * Comment for <code>RESULT_USER_DISABLED</code>
     */
    int RESULT_USER_DISABLED = 3;

    /**
     * Comment for <code>RESULT_USER_NOT_FOUND</code>
     */
    int RESULT_USER_NOT_FOUND = 1;

    /**
     * Comment for <code>RESULT_SERVICE_UNAVAILABLE</code>
     */
    int RESULTSERVICE_UNAVAILABLE = 4;

    /** Role name of component */
    String ROLE = UserAuthenticator.class.getName();

    /**
     * Authenticate user with name/password pair
     *
     * @param userName Name of user
     * @param password Password of user
     * @return Authentication result
     */
    int authenticate(String userName, String password);

    /**
     * Test if user name is available
     *
     * @param userName Name of user
     * @return True if user name is available
     */
    boolean isUserAvailable(String userName);
}
