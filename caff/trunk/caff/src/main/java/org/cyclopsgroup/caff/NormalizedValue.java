package org.cyclopsgroup.caff;

/**
 * A class that defines a primitive identifier
 *
 * @author jiaqi
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
