package org.cyclopsgroup.waterview.spi;

/**
 * Interface that resolve component with given name and type
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ComponentResolver
{
    /**
     * @param <T> Type of component
     * @param type Type of component
     * @param name Name of component
     * @return Instance of component. NULL if it's not found
     */
    <T> T findComponent(Class<T> type, String name);
}
