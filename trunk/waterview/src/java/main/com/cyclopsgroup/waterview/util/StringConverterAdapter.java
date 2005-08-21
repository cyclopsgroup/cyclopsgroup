/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.util;

import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;

class StringConverterAdapter implements Converter
{
    private StringConverter converter = new StringConverter();

    /**
     * Override method StringConverter in supper class
     *
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     */
    public Object convert(Class type, Object value)
    {
        if (type == String.class && value != null && value instanceof Date)
        {
            return DateConverter.FORMAT.format(value);
        }
        return converter.convert(type, value);
    }
}
