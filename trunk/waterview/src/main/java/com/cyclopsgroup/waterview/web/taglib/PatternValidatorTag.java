/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import java.util.regex.Pattern;

import com.cyclopsgroup.waterview.web.Field;
import com.cyclopsgroup.waterview.web.FieldValidator;
import com.cyclopsgroup.waterview.web.ValidationResult;

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
     * Override method PatternValidator in supper class
     *
     * @see com.evavi.fabric.ui.form.FieldValidator#validate(com.evavi.fabric.ui.form.Field)
     */
    public ValidationResult validate(Field field)
    {
        boolean success = Pattern.matches(getPattern(), field.getValue());
        return new ValidationResult(success, "Doesn't match pattern "
                + getPattern());
    }
}
