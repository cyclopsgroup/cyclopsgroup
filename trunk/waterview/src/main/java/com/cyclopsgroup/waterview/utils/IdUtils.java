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
package com.cyclopsgroup.waterview.utils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * ID related utilities
 */
public final class IdUtils
{
    private static int rollingNumber = 0;

    /**
     * Create new string id
     *
     * @return String id
     */
    public static long newLongId()
    {
        synchronized ( IdUtils.class )
        {
            rollingNumber = ( rollingNumber + 1 ) % 1024768;
        }
        long number = System.currentTimeMillis() * 1024768 + rollingNumber;
        return number;
    }

    /**
     * New unique string id
     *
     * @return String ID
     */
    public static String newStringId()
    {
        return "t" + newLongId();
    }
}
