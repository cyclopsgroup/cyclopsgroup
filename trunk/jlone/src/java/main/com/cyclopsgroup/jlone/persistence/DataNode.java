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
package com.cyclopsgroup.jlone.persistence;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Data node interface
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public interface DataNode
{
    /** Empty array */
    DataNode[] EMPTY_ARRAY = new DataNode[0];
    
    /**
     * Get child node with given name
     *
     * @param fieldName Child name
     * @return Child node object
     */
    DataNode getChild(String fieldName);

    /**
     * Get child nodes with given name
     *
     * @param fieldName Name of the children
     * @return Array of child nodes
     */
    DataNode[] getChildren(String fieldName);

    /**
     * Get input stream of value of a field
     *
     * @param fieldName Name of the field
     * @return InputStream of value
     */
    InputStream getFieldAsStream(String fieldName);

    /**
     * Get string value of field
     *
     * @param fieldName Name of the field
     * @return String value of the field
     */
    String getFieldAsString(String fieldName);

    /**
     * Set string value of the field
     *
     * @param fieldName Field name
     * @param value String value
     */
    void setField(String fieldName, String value);

    /**
     * Get the output stream of the field value to set field value
     *
     * @return OutputStream of value of the field
     */
    OutputStream setFieldAsStream();
}