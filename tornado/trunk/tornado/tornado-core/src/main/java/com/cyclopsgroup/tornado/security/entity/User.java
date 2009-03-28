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
package com.cyclopsgroup.tornado.security.entity;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * User entity bean
 */
public class User
    extends UserBase
{
    /**
     * @return Full name
     */
    public String getDisplayName()
    {
        StringBuffer sb = new StringBuffer( getFirstName() );
        if ( StringUtils.isNotEmpty( getMiddleName() ) )
        {
            sb.append( ' ' ).append( getMiddleName() );
        }
        return sb.append( ' ' ).append( getLastName() ).toString();
    }

    /**
     * @return Private password
     */
    public String getPrivatePassword()
    {
        String pwd = getPassword();
        return new String( Base64.decodeBase64( pwd.getBytes() ) );
    }

    /**
     * @param privatePassword
     */
    public void setPrivatePassword( String privatePassword )
    {
        String pwd = new String( Base64.encodeBase64( privatePassword.getBytes() ) );
        setPassword( pwd );
    }
}
