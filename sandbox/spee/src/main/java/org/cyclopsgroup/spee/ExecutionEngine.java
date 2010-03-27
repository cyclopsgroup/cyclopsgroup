package org.cyclopsgroup.spee;

/**
 * Global execution engine. This interface is the start point of code to access spee.
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface ExecutionEngine
{
    ExecutionContext findExecution( String executionId );

    ExecutionContext createExecution( String executionId );
}
