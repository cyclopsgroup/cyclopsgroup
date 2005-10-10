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
package com.cyclopsgroup.laputa.core;

import org.apache.commons.lang.enum.Enum;

/**
 * Member status
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class MemberStatus extends Enum
{
    /** Approved */
    public static MemberStatus APPROVED = new MemberStatus("approved");

    /** Killed by system admin */
    public static MemberStatus KILLED = new MemberStatus("killed");

    /** Application rejected */
    public static MemberStatus REJECTED = new MemberStatus("rejected");

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static long serialVersionUID = 3257565122447684152L;

    /** Suspended by system admin */
    public static MemberStatus SUSPENDED = new MemberStatus("suspended");

    /** Waiting for approval */
    public static MemberStatus WAITING = new MemberStatus("waiting");

    /**
     * Get value of status
     *
     * @param value String value
     * @return Value of status
     */
    public static MemberStatus valueOf(String value)
    {
        return (MemberStatus) getEnum(MemberStatus.class, value);
    }

    private MemberStatus(String value)
    {
        super(value);
    }
}
