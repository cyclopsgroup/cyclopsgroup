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
package com.cyclopsgroup.petri;

import org.apache.commons.lang.exception.NestableException;

/**
 * Workflow specified exception
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class WorkflowException extends NestableException
{
    /**
     * Constructor for class WorkflowException
     *
     * @param msg
     */
    public WorkflowException(String msg)
    {
        super(msg);
    }

    /**
     * Constructor for class WorkflowException
     *
     * @param msg
     * @param e
     */
    public WorkflowException(String msg, Throwable e)
    {
        super(msg, e);
    }

    /**
     * Constructor for class WorkflowException
     *
     * @param e
     */
    public WorkflowException(Throwable e)
    {
        super(e);
    }
}