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
package com.cyclopsgroup.tornado.portal.taglib;

import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.portal.HtmlControlAsset;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag for secure html control
 */
public class SecureControlTag
    extends TagSupport
{
    private String name;

    private String role;

    /**
     * Getter method for property name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for property role
     *
     * @return Returns the role.
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        HtmlControlAsset asset = new HtmlControlAsset( getName() );
        RuntimeData data = getRuntimeData();
        RuntimeUser user = RuntimeUser.getInstance( data );
        if ( StringUtils.isNotEmpty( getName() ) && !user.isAuthorized( asset ) )
        {
            return;
        }
        if ( StringUtils.isNotEmpty( getRole() ) )
        {
            String[] roles = StringUtils.split( getRole(), '|' );
            for ( int i = 0; i < roles.length; i++ )
            {
                String role = roles[i];
                if ( !user.hasRole( role ) )
                {
                    return;
                }
            }
        }
        invokeBody( output );
    }

    /**
     * Setter method for property name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Setter method for property role
     *
     * @param role The role to set.
     */
    public void setRole( String role )
    {
        this.role = role;
    }
}
