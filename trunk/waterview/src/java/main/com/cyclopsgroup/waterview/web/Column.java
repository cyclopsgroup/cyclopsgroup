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

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Column model
 */
public interface Column
{
    /** Empty column array */
    Column[] EMPTY_ARRAY = new Column[0];

    /**
     * Get cell body
     *
     * @param row Row object
     * @return Body content string
     * @throws Exception Throw it out
     */
    String getBody(Object row) throws Exception;

    /**
     * Get header
     *
     * @return Header content
     * @throws Exception
     */
    String getHeader() throws Exception;

    /**
     * Get unique name of column
     *
     * @return Unique name of column
     */
    String getName();

    /**
     * If the column is hidden by default
     *
     * @return True if it's hidden
     */
    boolean isHidden();

    /**
     * If the column is forced to be displayed
     *
     * @return True if the column is forced to be displayed
     */
    boolean isRequired();

    /**
     * Is column sortable
     *
     * @return True if column is sortable
     */
    boolean isSortable();
}
