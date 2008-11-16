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
package com.cyclopsgroup.waterview.tool;

import org.apache.commons.lang.enum.Enum;

/**
 * Tool lifecycle constants
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ToolLifecycle extends Enum
{

    /** Application level tool */
    public static final ToolLifecycle APPLICATION = new ToolLifecycle(
            "application");

    /** Request level tool */
    public static final ToolLifecycle REQUEST = new ToolLifecycle("request");

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4120851049289626419L;

    /** Session level tool */
    public static final ToolLifecycle SESSION = new ToolLifecycle("session");

    /**
     * User level tool
     */
    public static final ToolLifecycle USER = new ToolLifecycle("user");

    /**
     * Value of method with string name
     *
     * @param name String name
     * @return Constant object or null
     */
    public static final ToolLifecycle valueOf(String name)
    {
        return (ToolLifecycle) Enum.getEnum(ToolLifecycle.class, name);
    }

    private ToolLifecycle(String name)
    {
        super(name);
    }
}