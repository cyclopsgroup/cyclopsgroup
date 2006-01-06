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

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Field validation result
 */
public final class ValidationResult
{
    /** Empty array */
    public static final ValidationResult SUCCESS = new ValidationResult( true, StringUtils.EMPTY );

    private String errorMessage;

    private boolean success;

    /**
     * Constructor for class ValidationResult
     *
     * @param success Success or not
     * @param message Error message
     */
    public ValidationResult( boolean success, String message )
    {
        this.success = success;
        errorMessage = message;
    }

    /**
     * @return Returns the errorMessage.
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * @return Returns the success.
     */
    public boolean isSuccess()
    {
        return success;
    }

}
