package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Format implementation that parse and format fix length fields
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class FixLengthFormat<T>
    extends Format<T>
{
    private final char fill;

    private final int length;

    /**
     * @param beanType Type of bean
     */
    public FixLengthFormat( Class<T> beanType )
    {
        super( beanType );
        FixLengthType annotation = beanType.getAnnotation( FixLengthType.class );
        if ( annotation == null )
        {
            throw new IllegalArgumentException( "Type " + beanType + " isn't annotated with " + FixLengthType.class );
        }
        fill = annotation.fill();
        length = annotation.length();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void populate( T object, Reader reader )
        throws IOException
    {
        char[] line = new char[length];
        reader.read( line );
        populate( object, line );
    }
    
    

    private void populate( T object, char[] line )
    {
        
    }

    /**
     * @inheritDoc
     */
    @Override
    public void print( T object, Writer output )
        throws IOException
    {
        // TODO Auto-generated method stub

    }
}
