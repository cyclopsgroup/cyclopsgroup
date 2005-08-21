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
package com.cyclopsgroup.waterview.web;

import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Display policy for column
 */
public final class ColumnDisplay extends Enum
{
    /**
     * Forced to display
     */
    public static ColumnDisplay FORCED = new ColumnDisplay("forced");

    /**
     * Optionally displayed
     */
    public static ColumnDisplay HIDDEN = new ColumnDisplay("hidden");

    /**
     * Optional hidden
     */
    public static ColumnDisplay OPTIONAL = new ColumnDisplay(
            "optional");

    /**
     * Get instance from a string
     *
     * @param value String value
     * @return Instance or null
     */
    public static ColumnDisplay valueOf(String value)
    {
        ColumnDisplay ret= (ColumnDisplay) getEnum(ColumnDisplay.class, value);
        if(ret == null)
        {
            throw new IllegalArgumentException(value + " is not a legal display option");
        }
        return ret;
    }

    private ColumnDisplay(String value)
    {
        super(value);
    }
    
    public String toString()
    {
        return getName();
    }
}
