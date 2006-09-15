/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
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
    @SuppressWarnings("unchecked")
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
