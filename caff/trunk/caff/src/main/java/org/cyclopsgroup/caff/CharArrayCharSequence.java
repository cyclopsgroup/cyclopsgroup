package org.cyclopsgroup.caff;

public class CharArrayCharSequence
    implements CharSequence
{
    private final char[] chars;

    private final int length;

    private final int start;

    public CharArrayCharSequence( char[] chars )
    {
        this( chars, 0, chars.length );
    }

    public CharArrayCharSequence( char[] chars, int start, int length )
    {
        this.chars = chars;
        this.start = start;
        this.length = length;
    }

    /**
     * @inheritDoc
     */
    @Override
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
    @Override
    public int length()
    {
        return chars.length - start;
    }

    /**
     * @inheritDoc
     */
    @Override
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
