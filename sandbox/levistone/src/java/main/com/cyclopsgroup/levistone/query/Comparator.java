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
package com.cyclopsgroup.levistone.query;

import org.apache.commons.lang.enum.Enum;

/**
 * Comparator model object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class Comparator extends Enum
{
    /** Equal comparator */
    public static Comparator EQUAL = new Comparator("equal");

    /** Greater than comparator */
    public static Comparator GREATER = new Comparator("greater");

    /** Greater equal than comparator */
    public static Comparator GREATER_EQUAL = new Comparator("greater-equal");

    /** In comparator */
    public static Comparator IN = new Comparator("in");

    /** Less than comparator */
    public static Comparator LESS = new Comparator("less");

    /** Less equal than comparator */
    public static Comparator LESS_EQUAL = new Comparator("less-equal");

    /** Not equal comparator */
    public static Comparator NOT_EQUAL = new Comparator("not-equal");

    /** Not in comparator */
    public static Comparator NOT_IN = new Comparator("not-in");

    /**
     * Get comparator instance with given value
     *
     * @param value Comparator value
     * @return Comparator instance
     */
    public static Comparator valueOf(String value)
    {
        return (Comparator) Enum.getEnum(Comparator.class, value);
    }

    /**
     * Constructor for class Comparator
     *
     * @param value Value of this comparator
     */
    private Comparator(String value)
    {
        super(value);
    }
}