/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import com.cyclopsgroup.waterview.web.FieldValidator;
import com.cyclopsgroup.waterview.web.TypeFieldValidator;

public class TypeValidatorTag extends BaseValidatorTag
{

    /**
     * Overwrite or implement method createValidator()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.BaseValidatorTag#createValidator()
     */
    protected FieldValidator createValidator() throws Exception
    {
        return TypeFieldValidator.INSTANCE;
    }
}
