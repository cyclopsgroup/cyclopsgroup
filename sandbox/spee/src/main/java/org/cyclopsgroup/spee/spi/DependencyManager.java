package org.cyclopsgroup.spee.spi;

public interface DependencyManager
{
    <T> T findComponent( String name, Class<T> type );

    <T> T findComponent( Class<T> type );
}
