/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.web.FieldValidator;

public abstract class BaseValidatorTag extends TagSupport
{

    protected abstract FieldValidator createValidator() throws Exception;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        FieldValidator validator = createValidator();
        if (getParent() instanceof FieldTag)
        {
            ((FieldTag) getParent()).getField().setValidator(validator);
        }
        else if (getParent() instanceof ValidatorsTag)
        {
            ((ValidatorsTag) getParent()).addValidator(validator);
        }
        else
        {
            throw new JellyTagException(
                    "Parent tag must be Field or Validators");
        }
    }

}
