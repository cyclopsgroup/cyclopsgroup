package org.cyclopsgroup.spee;

public interface EngineContext
{
    <T> T findComponent(String name, Class<T> type);
    
    <T> T findComponent(Class<T> type);
    
    ExecutionContext findExecution(String executionId);
    
    ExecutionContext createExecution(String executionId);
}
