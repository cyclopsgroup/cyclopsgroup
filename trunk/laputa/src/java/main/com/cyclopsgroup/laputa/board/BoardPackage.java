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
 * Board package
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BoardPackage extends BaseEntity
{

    /**
     * 
     * @uml.property name="name" multiplicity="(0 1)"
     */
    private String name;

    /**
     * 
     * @uml.property name="parentId" multiplicity="(0 1)"
     */
    private String parentId;

    /**
     * 
     * @uml.property name="path" multiplicity="(0 1)"
     */
    private String path;

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
     * Getter method for parentId
     * 
     * @return Returns the parentId.
     * 
     * @uml.property name="parentId"
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Getter method for path
     * 
     * @return Returns the path.
     * 
     * @uml.property name="path"
     */
    public String getPath() {
        return path;
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
     * Setter method for parentId
     * 
     * @param parentId The parentId to set.
     * 
     * @uml.property name="parentId"
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Setter method for path
     * 
     * @param path The path to set.
     * 
     * @uml.property name="path"
     */
    public void setPath(String path) {
        this.path = path;
    }

}