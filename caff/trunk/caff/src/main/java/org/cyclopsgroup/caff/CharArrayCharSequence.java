package org.cyclopsgroup.caff;

/**
 * CharSequence implementation based on a char array
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class CharArrayCharSequence
    implements CharSequence
{
    private final char[] chars;

    private final int length;

    private final int start;

    /**
     * @param chars Char array of the whole content
     */
    public CharArrayCharSequence( char[] chars )
    {
        this( chars, 0, chars.length );
    }

    /**
     * @param chars Char array that contains the content
     * @param start Start position of content
     * @param length Length of content
     */
    public CharArrayCharSequence( char[] chars, int start, int length )
    {
        this.chars = chars;
        this.start = start;
        this.length = length;
    }

    /**
     * @inheritDoc
     */
    public char charAt( int index )
    {
        if ( index >= length )
        {
            throw new IndexOutOfBoundsException( "Index " + index + " is greater than length " + length );
        }
        return chars[index + start];
    }

    /**
     * @inheritDoc
     */
    public int length()
    {
        return chars.length - start;
    }

    /**
     * @inheritDoc
     */
    public CharSequence subSequence( int start, int end )
    {
        if ( end > this.length )
        {
            throw new IndexOutOfBoundsException( "End " + end + " exceeds limit " + this.length );
        }
        if ( end > start )
        {
            throw new IllegalArgumentException( "Invalid input start=" + start + ", end=" + end );
        }
        return new CharArrayCharSequence( chars, this.start + start, end - start );
    }
}
