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

import com.cyclopsgroup.lloyd.User;

/**
 * Default user implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultUser extends DefaultSecurityEntity implements User
{
    private String fullName;

    private String password;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.User#getFullName()
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.User#getPassword()
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.User#setFullName(java.lang.String)
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.User#setPassword(java.lang.String)
     */
    public void setPassword(String newPassword)
    {
        this.password = newPassword;
    }
}
