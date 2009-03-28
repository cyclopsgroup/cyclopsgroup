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

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class SimpleAsset
    implements Asset
{
    private PermissionType permissionType;

    private String expression;

    /**
     * Constructor for class SimpleAsset
     *
     * @param expression Expression for asset
     * @param type Permission type
     */
    public SimpleAsset( String expression, PermissionType type )
    {
        this.expression = expression;
        permissionType = type;
    }

    /**
     * Override method getPermissionType in class SimpleAbstractAsset
     *
     * @see com.cyclopsgroup.tornado.security.Asset#getPermissionType()
     */
    public PermissionType getPermissionType()
    {
        return permissionType;
    }

    /**
     * Override method authorize in class SimpleAbstractAsset
     *
     * @see com.cyclopsgroup.tornado.security.Asset#authorize(com.cyclopsgroup.tornado.security.Permission)
     */
    public boolean authorize( Permission permission )
    {
        if ( !( permission instanceof SimplePermission ) )
        {
            return false;
        }
        SimplePermission sp = (SimplePermission) permission;
        if ( StringUtils.equals( expression, sp.getExpression() ) )
        {
            return true;
        }
        String pattern = '^' + sp.getExpression() + '$';
        return Pattern.matches( pattern, expression );
    }
}
