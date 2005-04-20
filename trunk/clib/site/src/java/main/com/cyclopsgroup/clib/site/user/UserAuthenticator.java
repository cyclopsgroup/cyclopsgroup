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
package com.cyclopsgroup.clib.site.user;

/**
 * User authenticator
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface UserAuthenticator
{
    /** Role name of the component */
    String ROLE = UserAuthenticator.class.getName();

    /**
     * Authenticate user with a password
     *
     * @param userName Name of the user
     * @param password Password of the user
     * @return True if user name and password matched
     * @throws Exception
     */
    boolean authenticate(String userName, String password) throws Exception;

    /**
     * Check if the user name exsits
     *
     * @param userName Name of the user
     * @return True if user is found
     * @throws Exception Throw it out
     */
    boolean exsit(String userName) throws Exception;

    /**
     * Fetch the user information with given name
     *
     * @param userName User name
     * @return User information
     * @throws Exception Throw it out
     */
    User fetch(String userName) throws Exception;
}
