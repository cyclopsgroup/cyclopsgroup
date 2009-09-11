package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.AccessibleObject;

import org.cyclopsgroup.caff.CharIterator;
import org.cyclopsgroup.caff.ref.ValueReference;
import org.cyclopsgroup.caff.ref.ValueReferenceScanner;

/**
 * Internal class that really does CSV format and parsing
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * @param <T> Type of bean to convert from/to
 */
class CSVImpl<T>
{
    private final int fields;

    CSVImpl( Class<T> beanType )
    {
        CSVType typeAnnotation = beanType.getAnnotation( CSVType.class );
        if ( typeAnnotation == null )
        {
            throw new IllegalArgumentException( "Type " + beanType + " isn't annotated with " + CSVType.class );
        }
        fields = typeAnnotation.fields();
        if ( fields <= 0 )
        {
            throw new IllegalArgumentException( "Type " + beanType + " defines invalid number of CSV fields: " + fields );
        }
        ValueReferenceScanner<T> scanner = new ValueReferenceScanner<T>( beanType );
        scanner.scanForAnnotation( CSVField.class, new ValueReferenceScanner.Listener<T, CSVField>()
        {
            public void handleReference( ValueReference<T> reference, CSVField field, AccessibleObject access )
            {

            }
        } );
    }

    void populate( T bean, CharIterator in )
        throws IOException
    {
        CSVParser parser = new CSVParser()
        {
            @Override
            protected void handleField( int position, CharSequence content )
                throws IOException
            {
                // TODO Auto-generated method stub

            }
        };
    }

    void print( T bean, Writer out )
    {

    }
}
