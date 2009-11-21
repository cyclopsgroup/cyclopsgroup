package org.cyclopsgroup.eulerer.math;

/**
 * Interface to allow caller to specify behavior for element in matrix
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of element in matrix
 */
public interface MatrixInspector<T>
{
    /**
     * Traverse a matrix
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param value Value of element
     * @return Possibly a new value if {@link Matrix#transform(MatrixInspector)} is called
     */
    T inspect( int x, int y, T value );
}
