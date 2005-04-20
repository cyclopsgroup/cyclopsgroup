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

/**
 * Member in this bbs system
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Member extends BaseEntity
{

    private String displayName;

    /**
     * 
     * @uml.property name="name" multiplicity="(0 1)"
     */
    private String name;

    /**
     * 
     * @uml.property name="statusId" multiplicity="(0 1)"
     */
    private String statusId;

    /**
     * Getter method for displayName
     *
     * @return Returns the displayName.
     */
    public String getDisplayName()
    {
        return displayName;
    }

    /**
     * Getter method for name
     * 
     * @return Returns the name.
     * 
     * @uml.property name="name"
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get status object
     *
     * @return MemberStatus object
     */
    public MemberStatus getStatus()
    {
        return MemberStatus.valueOf(getStatusId());
    }

    /**
     * Getter method for status
     * 
     * @return Returns the status.
     * 
     * @uml.property name="statusId"
     */
    public String getStatusId()
    {
        return statusId;
    }

    /**
     * Setter method for displayName
     *
     * @param displayName The displayName to set.
     */
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    /**
     * Setter method for name
     * 
     * @param name The name to set.
     * 
     * @uml.property name="name"
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter method for status
     * 
     * @param status The status to set.
     * 
     * @uml.property name="statusId"
     */
    public void setStatusId(String status)
    {
        this.statusId = status;
    }

}
