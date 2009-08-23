package org.cyclopsgroup.caff.format;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Base class that knows how to format/parse beans
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * @param <T> Type of bean to format or parse
 */
public abstract class Format<T>
{
    static Reader createCharSequenceReader( CharSequence content )
    {
        char[] chars = new char[content.length()];
        for ( int i = 0; i < content.length(); i++ )
        {
            chars[i] = content.charAt( i );
        }
        return new CharArrayReader( chars );
    }

    private final Class<T> beanType;

    protected Format( Class<T> beanType )
    {
        if ( beanType == null )
        {
            throw new NullPointerException( "Type of bean can't be NULL" );
        }
        this.beanType = beanType;
    }

    T createBean()
    {
        try
        {
            return beanType.newInstance();
        }
        catch ( InstantiationException e )
        {
            throw new InvalidTypeException( "Can't create instance of " + beanType, e );
        }
        catch ( IllegalAccessException e )
        {
            throw new InvalidTypeException( "Can't create instance of " + beanType, e );
        }
    }

    /**
     * @param object
     * @return
     * @throws IOException
     */
    public char[] formatToCharArray( T object )
        throws IOException
    {
        CharArrayWriter output = new CharArrayWriter();
        print( object, output );
        output.flush();
        return output.toCharArray();
    }

    /**
     * @param object
     * @return
     * @throws IOException
     */
    public String formatToString( T object )
        throws IOException
    {
        return new String( formatToCharArray( object ) );
    }

    /**
     * Parse given
     * 
     * @param content
     * @return New instance of bean
     * @throws IOException
     */
    public T parse( CharSequence content )
        throws IOException
    {
        return parse( createCharSequenceReader( content ) );
    }

    /**
     * @param reader Reader that has content of bean to parse
     * @return New instance of bean
     * @throws IOException
     */
    public T parse( Reader reader )
        throws IOException
    {
        T bean = createBean();
        populate( bean, reader );
        return bean;
    }

    public abstract void populate( T object, Reader reader )
        throws IOException;

    public abstract void print( T object, Writer output )
        throws IOException;
}
