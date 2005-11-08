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
package com.cyclopsgroup.courselist.entity;

import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Course status
 */
public final class CourseStatus
    extends Enum
{
    /** Dropped course */
    public static CourseStatus DROPPED = new CourseStatus( "dropped" );

    /** Finished status */
    public static CourseStatus FINISHED = new CourseStatus( "finished" );

    /** No status */
    public static CourseStatus NONE = new CourseStatus( "none" );

    /**Taking status */
    public static CourseStatus TAKING = new CourseStatus( "taking" );

    /**
     * Get instance from value
     *
     * @param value Value
     * @return Instance or null
     */
    public static CourseStatus valueOf( String value )
    {
        return (CourseStatus) getEnum( CourseStatus.class, value );
    }

    private CourseStatus( String value )
    {
        super( value );
    }
}
