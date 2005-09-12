/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Date commons beanutils converter
 */
class DateConverter
    implements Converter
{
    /** Date format to format date type */
    static final SimpleDateFormat FORMAT = new SimpleDateFormat( "MM/dd/yyyy" );

    /**
     * Override method DateConverter in supper class
     *
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     */
    public Object convert( Class type, Object object )
    {
        if ( object == null )
        {
            throw new ConversionException( "Value is null" );
        }
        if ( type == Date.class )
        {
            if ( object instanceof Date )
            {
                return object;
            }
            try
            {
                return FORMAT.parse( object.toString().trim() );
            }
            catch ( Exception e )
            {
                throw new ConversionException( object.toString() + " is not recognizable as a date MM/dd/yyyy", e );
            }
        }
        else if ( type == String.class && object instanceof Date )
        {
            return FORMAT.format( (Date) object );
        }
        else
        {
            throw new ConversionException( "Unkonwn conversion type " + object.getClass() + "->" + type );
        }
    }
}
