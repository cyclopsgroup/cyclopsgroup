/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Validator to check type
 */
public class TypeFieldValidator
    implements FieldValidator
{
    /** Static instance */
    public static final TypeFieldValidator INSTANCE = new TypeFieldValidator();

    /**
     * Overwrite or implement method validate()
     *
     * @see com.cyclopsgroup.waterview.web.FieldValidator#validate(com.cyclopsgroup.waterview.web.Field)
     */
    public ValidationResult validate( Field field )
    {
        try
        {
            Object value = TypeUtils.convert( field.getValue(), field.getType() );
            String string = TypeUtils.toString( value );
            return StringUtils.equals( string, field.getValue() ) ? ValidationResult.SUCCESS
                                                                 : new ValidationResult( false, "Invalid "
                                                                     + field.getType() + " format" );
        }
        catch ( Exception e )
        {
            return new ValidationResult( false, e.getMessage() );
        }
    }
}
