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
package com.cyclopsgroup.waterview.core;

import org.apache.commons.lang.enum.Enum;

/**
 * Resource type enumeration
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ResourceType extends Enum
{
    /**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -8545161308309826489L;

	/** File resource type */
    public static final ResourceType FILE = new ResourceType("file");

    /** Classpath resource type */
    public static final ResourceType RESOURCE = new ResourceType("resource");

    /** URL resource type */
    public static final ResourceType URL = new ResourceType("url");

    /**
     * Get object
     *
     * @param value Value of enum
     * @return resource type object
     */
    public static ResourceType valueOf(String value)
    {
        return (ResourceType) getEnum(ResourceType.class, value);
    }

    private ResourceType(String value)
    {
        super(value);
    }
}
