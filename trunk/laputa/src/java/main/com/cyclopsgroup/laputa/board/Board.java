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
package com.cyclopsgroup.laputa.board;

import com.cyclopsgroup.laputa.core.BaseEntity;

/**
 * Board entity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Board extends BaseEntity
{

    /**
     * 
     * @uml.property name="name" multiplicity="(0 1)"
     */
    private String name;

    /**
     * 
     * @uml.property name="ownerId" multiplicity="(0 1)"
     */
    private String ownerId;

    /**
     * 
     * @uml.property name="packageId" multiplicity="(0 1)"
     */
    private String packageId;

    /**
     * 
     * @uml.property name="packagePath" multiplicity="(0 1)"
     */
    private String packagePath;

    /**
     * Getter method for name
     * 
     * @return Returns the name.
     * 
     * @uml.property name="name"
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for ownerId
     * 
     * @return Returns the ownerId.
     * 
     * @uml.property name="ownerId"
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Getter method for packageId
     * 
     * @return Returns the packageId.
     * 
     * @uml.property name="packageId"
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Getter method for packagePath
     * 
     * @return Returns the packagePath.
     * 
     * @uml.property name="packagePath"
     */
    public String getPackagePath() {
        return packagePath;
    }

    /**
     * Setter method for name
     * 
     * @param name The name to set.
     * 
     * @uml.property name="name"
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for ownerId
     * 
     * @param ownerId The ownerId to set.
     * 
     * @uml.property name="ownerId"
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Setter method for packageId
     * 
     * @param packageId The packageId to set.
     * 
     * @uml.property name="packageId"
     */
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    /**
     * Setter method for packagePath
     * 
     * @param packagePath The packagePath to set.
     * 
     * @uml.property name="packagePath"
     */
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

}
