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
package com.cyclopsgroup.waterview;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Valve for pipeline
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class Valve extends AbstractLogEnabled
{

    /**
     * Empty valve array
     * 
     * @uml.property name="eMPTY_ARRAY"
     * @uml.associationEnd multiplicity="(0 -1)"
     */
    Valve[] EMPTY_ARRAY = new Valve[0];

    /**
     * Logger object
     * 
     * @uml.property name="logger"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    protected Log logger = LogFactory.getLog(getClass());

    /**
     * 
     * @uml.property name="next"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    private Valve next;

    /**
     * Getter method for next
     * 
     * @return Returns the next.
     * 
     * @uml.property name="next"
     */
    public Valve getNext() {
        return next;
    }


    /**
     * Invoke this valve.
     * Make sure to invokeNext valve
     * 
     * @param runtime Runtime context
     * @throws Exception Throw it out
     */
    public abstract void invoke(UIRuntime runtime) throws Exception;

    /**
     * Invoke next valve
     *
     * @param runtime Runtime object
     * @throws Exception Throw it out
     */
    protected final void invokeNext(UIRuntime runtime) throws Exception
    {
        if (getNext() != null)
        {
            getNext().invoke(runtime);
        }
    }

    /**
     * Setter method for next
     * 
     * @param next The next to set.
     * 
     * @uml.property name="next"
     */
    public void setNext(Valve next) {
        this.next = next;
    }

}