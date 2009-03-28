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

import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public final class UserAuthenticationResult
    extends Enum
{
    /** Success result */
    public static UserAuthenticationResult SUCCESS = new UserAuthenticationResult( "success" );

    /** No such user result */
    public static UserAuthenticationResult NO_SUCH_USER = new UserAuthenticationResult( "nosuchuser" );

    /** Wrong password result */
    public static UserAuthenticationResult WRONG_PASSWORD = new UserAuthenticationResult( "wrongpassword" );

    /** No service available to authenticate user */
    public static UserAuthenticationResult NOSERVICE = new UserAuthenticationResult( "noservice" );

    /** Internal error */
    public static UserAuthenticationResult ERROR = new UserAuthenticationResult( "error" );

    /**
     * Constructor for class UserAuthenticationResult
     *
     * @param name Name of result
     */
    private UserAuthenticationResult( String name )
    {
        super( name );
    }

    /**
     * Get instance of enum
     * 
     * @param value Value of it
     * @return Instance or null if not found
     */
    public static UserAuthenticationResult valueOf( String value )
    {
        return (UserAuthenticationResult) getEnum( UserAuthenticationResult.class, value );
    }
}
