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
package com.cyclopsgroup.lloyd;

/**
 * Base interface of security entity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface SecurityEntity
{
    /**
     * Get unique id of this entity
     *
     * @return Unique id
     */
    long getId();

    /**
     * Get name of this entity
     *
     * @return Name of entity
     */
    String getName();

    /**
     * If this user disabled or not
     * 
     * @return If the user is disabled
     * 
     * @uml.property name="disabled"
     */
    boolean isDisabled();

    /**
     * Setter for disabled property
     * 
     * @param disabled If it's disabled
     * 
     * @uml.property name="disabled"
     */
    void setDisabled(boolean disabled);
}