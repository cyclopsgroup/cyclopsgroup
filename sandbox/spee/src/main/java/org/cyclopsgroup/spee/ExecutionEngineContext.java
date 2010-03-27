package org.cyclopsgroup.spee;

/**
 * Engine level global context from where flow can access global resource
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface ExecutionEngineContext
{
    <T> T findComponent( String name, Class<T> type );

    <T> T findComponent( Class<T> type );
}
