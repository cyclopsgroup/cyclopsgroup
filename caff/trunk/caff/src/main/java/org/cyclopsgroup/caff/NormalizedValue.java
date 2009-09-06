package org.cyclopsgroup.caff;

/**
 * A class that defines a primitive identifier
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * @param <T> Type of identifier
 */
public interface NormalizedValue<T>
{
    /**
     * @return Unified identifier of this value
     */
    T getIdentifier();
}
