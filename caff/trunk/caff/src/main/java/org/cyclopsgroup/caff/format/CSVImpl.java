package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.io.Writer;

import org.cyclopsgroup.caff.CharIterator;

/**
 * Internal class that really does CSV format and parsing
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * @param <T> Type of bean to convert from/to
 */
class CSVImpl<T>
{

    CSVImpl( Class<T> beanType )
    {

    }

    void populate( T bean, CharIterator in )
        throws IOException
    {
        
    }

    void print( T bean, Writer out )
    {

    }
}
