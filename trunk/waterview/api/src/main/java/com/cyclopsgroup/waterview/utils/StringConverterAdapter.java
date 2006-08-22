/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.utils;

import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Adapter to do backward conversion in commons-beanutils converter
 */
class StringConverterAdapter
    implements Converter
{
    private StringConverter converter = new StringConverter();

    /**
     * Override method StringConverter in supper class
     *
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Object convert( Class type, Object value )
    {
        if ( type == String.class && value != null )
        {
            if ( value instanceof Date )
            {
                return DateConverter.FORMAT.format( value );
            }
            if ( value instanceof Enum )
            {
                return ( (Enum) value ).getName();
            }
        }
        return converter.convert( type, value );
    }
}
