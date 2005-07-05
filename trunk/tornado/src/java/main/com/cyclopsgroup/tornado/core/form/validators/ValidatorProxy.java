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
package com.cyclopsgroup.tornado.core.form.validators;

import java.util.Iterator;
import java.util.Vector;

import com.cyclopsgroup.tornado.core.form.Field;
import com.cyclopsgroup.tornado.core.form.FieldValidationResult;
import com.cyclopsgroup.tornado.core.form.FieldValidator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Proxy of many validators
 */
public class ValidatorProxy implements FieldValidator
{
    public static final String NAME = ValidatorProxy.class.getName();

    private Vector validators = new Vector();

    /**
     * Overwrite or implement method validate()
     * @see com.cyclopsgroup.tornado.core.form.FieldValidator#validate(com.cyclopsgroup.tornado.core.form.Field)
     */
    public FieldValidationResult validate(Field field)
    {
        FieldValidationResult fvr = new FieldValidationResult(field
                .getDefinition());
        for (Iterator i = validators.iterator(); i.hasNext();)
        {
            FieldValidator validator = (FieldValidator) i.next();
            FieldValidationResult result = validator.validate(field);
            if (result.isFailed())
            {
                fvr.setFailed(true);
            }
            String[] msgs = result.getErrorMessages();
            for (int j = 0; j < msgs.length; j++)
            {
                String msg = msgs[j];
                fvr.addErrorMessage(msg);
            }
        }
        return fvr;
    }
}
