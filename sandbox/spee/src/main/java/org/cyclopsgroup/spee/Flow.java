package org.cyclopsgroup.spee;

import java.io.Serializable;

/**
 * Flow is the definition of workflow. The way workflow is defined is to implement this interface with following
 * limitations:
 * <ul>
 * <li>Only primitive or Serializable objects can be created</li>
 * </ul>
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T>
 */
public interface Flow<T extends Serializable>
{
    T execute( ExecutionContext exe );
}
