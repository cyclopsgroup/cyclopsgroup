/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.web.Field;
import com.cyclopsgroup.waterview.web.FieldValidator;
import com.cyclopsgroup.waterview.web.ValidationResult;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Validator group tag
 */
public class ValidatorsTag extends BaseValidatorTag implements FieldValidator
{
    private HashSet validators;

    /**
     * Add validator
     *
     * @param validator
     */
    public void addValidator(FieldValidator validator)
    {
        validators.add(validator);
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.BaseValidatorTag#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        validators = new HashSet();
        super.processTag(output);
    }

    /**
     * Overwrite or implement method createValidator()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.BaseValidatorTag#createValidator()
     */
    protected FieldValidator createValidator() throws Exception
    {
        return this;
    }

    /**
     * Overwrite or implement method validate()
     *
     * @see com.cyclopsgroup.waterview.web.FieldValidator#validate(com.cyclopsgroup.waterview.web.Field)
     */
    public ValidationResult validate(Field field)
    {
        for (Iterator i = validators.iterator(); i.hasNext();)
        {
            FieldValidator validator = (FieldValidator) i.next();
            ValidationResult result = validator.validate(field);
            if (result != null && !result.isSuccess())
            {
                return result;
            }
        }
        return ValidationResult.SUCCESS;
    }
}