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
package com.cyclopsgroup.tornado.portal;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Portal service
 */
public interface PortalService
{
    /**
     * Role of service
     */
    String ROLE = PortalService.class.getName();

    /** Theme name for unset theme */
    String UNSET_THEME_NAME = "!unset!";

    /** User theme name */
    String USER_THEME_NAME = "tornado.user.theme";

    /**
     * Find user preference based on userId
     *
     * @param userId User id
     * @return UserPreference or null
     * @throws Exception Throw it out
     */
    UserPreference findUserPreference( String userId )
        throws Exception;
}
