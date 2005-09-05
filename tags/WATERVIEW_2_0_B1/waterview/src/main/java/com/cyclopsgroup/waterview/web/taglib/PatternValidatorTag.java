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
package com.cyclopsgroup.waterview.web.taglib;

import java.util.regex.Pattern;

import com.cyclopsgroup.waterview.web.Field;
import com.cyclopsgroup.waterview.web.FieldValidator;
import com.cyclopsgroup.waterview.web.ValidationResult;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Pattern validator tag
 */
public class PatternValidatorTag extends BaseValidatorTag implements
        FieldValidator
{
    private String pattern;

    /**
     * Overwrite or implement method createValidator()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.BaseValidatorTag#createValidator()
     */
    protected FieldValidator createValidator() throws Exception
    {
        requireAttribute("pattern");
        return this;
    }

    /**
     * @return Returns the pattern.
     */
    public String getPattern()
    {
        return pattern;
    }

    /**
     * @param pattern The pattern to set.
     */
    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    /**
     * Overwrite or implement method validate()
     *
     * @see com.cyclopsgroup.waterview.web.FieldValidator#validate(com.cyclopsgroup.waterview.web.Field)
     */
    public ValidationResult validate(Field field)
    {
        boolean success = Pattern.matches(getPattern(), field.getValue());
        return new ValidationResult(success, "Doesn't match pattern "
                + getPattern());
    }
}
