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
package com.cyclopsgroup.tornado.core.form;

import java.util.HashSet;

import org.apache.commons.lang.ArrayUtils;

public class FieldValidationResult
{
    private FieldDefinition definition;

    private HashSet errorMessages = new HashSet();

    private boolean failed;

    public FieldValidationResult(FieldDefinition definition)
    {
        this.definition = definition;
    }

    public void addErrorMessage(String message)
    {
        errorMessages.add(message);
    }

    /**
     * @return Returns the definition.
     */
    public FieldDefinition getDefinition()
    {
        return definition;
    }

    public String[] getErrorMessages()
    {
        return (String[]) errorMessages.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * @return Returns the failed.
     */
    public boolean isFailed()
    {
        return failed;
    }

    /**
     * @param failed The failed to set.
     */
    public void setFailed(boolean failed)
    {
        this.failed = failed;
    }
}
