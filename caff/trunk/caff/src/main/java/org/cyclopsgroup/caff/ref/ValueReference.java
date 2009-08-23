package org.cyclopsgroup.caff.ref;

/**
 * Interface that allow to read a value from given owner or write value to owner
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of value to get and set
 */
public interface ValueReference<T>
{
    /**
     * @return A unique name for this holder
     */
    String getName();

    /**
     * @return True if value really is readable
     */
    boolean isReadable();

    /**
     * @return True if value really is writable
     */
    boolean isWritable();

    /**
     * @param owner Object from which value is read
     * @return Reading result
     * @throws AccessFailureException If reading operation failed
     */
    T readValue( Object owner )
        throws AccessFailureException;

    /**
     * @param value Value to write to owner
     * @param owner Owner object where value is written to
     * @throws AccessFailureException If writing operation failed
     */
    void writeValue( T value, Object owner )
        throws AccessFailureException;
}
