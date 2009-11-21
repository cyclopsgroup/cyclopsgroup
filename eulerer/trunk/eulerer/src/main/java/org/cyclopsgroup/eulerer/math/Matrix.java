package org.cyclopsgroup.eulerer.math;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * Two dimention'ed data structure
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of element in matrix
 */
public class Matrix<T>
{
    private class Entry
    {
        private final T value;

        private final int x;

        private final int y;

        private Entry( T value, int x, int y )
        {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    private final List<Entry> list;

    /**
     * Width of matrix
     */
    public final int width;

    /**
     * Height of matrix
     */
    public final int height;

    /**
     * @param width Number of elements in a row
     * @param height Number of rows
     * @param initialValue Initial value that fills every slot in the beginning
     */
    public Matrix( int width, int height, T initialValue )
    {
        Validate.isTrue( width > 0, "Invalid width value " + width );
        Validate.isTrue( height > 0, "Invalid height value " + height );
        this.width = width;
        this.height = height;
        list = new ArrayList<Entry>( width * height );
        for ( int y = 0; y < height; y++ )
        {
            for ( int x = 0; x < width; x++ )
            {
                list.add( new Entry( initialValue, x, y ) );
            }
        }
    }

    private Matrix( int width, int height, List<Entry> list )
    {
        this.width = width;
        this.height = height;
        this.list = list;
    }

    /**
     * @param width Number of elements in a row
     * @param height Number of rows in matrix
     */
    public Matrix( int width, int height )
    {
        this( width, height, (T) null );
    }

    /**
     * Get value on given coordinate
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Value on that position
     */
    public T get( int x, int y )
    {
        Validate.isTrue( x >= 0 && y >= 0 && x <= width && y <= height );
        return list.get( y * width + x ).value;
    }

    /**
     * Set value of given coordinate
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param value Value to set
     * @return Old value before setting
     */
    public T set( int x, int y, T value )
    {
        Validate.isTrue( x >= 0 && y >= 0 && x <= width && y <= height );
        T oldValue = get( x, y );
        list.set( y * width + x, new Entry( value, x, y ) );
        return oldValue;
    }

    /**
     * Iterate through all elements in matrix
     *
     * @param inspector A call back interface called for every elements
     */
    public void traverse( MatrixInspector<T> inspector )
    {
        for ( Entry entry : list )
        {
            inspector.inspect( entry.x, entry.y, entry.value );
        }
    }

    /**
     * Create a new Matrix with same size, but value is what inspector returns
     *
     * @param inspector To calculate the value for a given coordinate
     * @return New matrix
     */
    public Matrix<T> transform( MatrixInspector<T> inspector )
    {
        List<Entry> newList = new ArrayList<Entry>( width * height );
        for ( Entry entry : list )
        {
            newList.add( new Entry( inspector.inspect( entry.x, entry.y, entry.value ), entry.x, entry.y ) );
        }
        return new Matrix<T>( width, height, newList );
    }
}
