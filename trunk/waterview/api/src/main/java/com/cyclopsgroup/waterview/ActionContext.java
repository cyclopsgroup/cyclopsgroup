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
package com.cyclopsgroup.waterview;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Context for an action
 */
public interface ActionContext
{
    /** Name of fail cause in context */
    String FAIL_CAUSE = "failCause";

    /** Name of fail message in context */
    String FAIL_MESSAGE = "failMessage";

    /**
     * @param message Message to add
     */
    void addMessage( String message );

    /**
     * Error because an input is not correct
     *
     * @param inputName Name of input
     * @param errorMessage Error message
     */
    void error( String inputName, String errorMessage );

    /**
     * Stop the action
     */
    void fail();

    /**
     * Fail because given message
     *
     * @param errorMessage Error message
     */
    void fail( String errorMessage );

    /**
     * Fail because given message and throwable
     *
     * @param errorMessage Error message
     * @param throwable Throwable object
     */
    void fail( String errorMessage, Throwable throwable );

    /**
     * Fail because of an exception
     *
     * @param throwable Throwable object
     */
    void fail( Throwable throwable );
}
