package org.cyclopsgroup.spee;

import java.util.concurrent.TimeUnit;

public interface FlowFuture<T>
{
    ExecutionContext getExecution();
    
    T getResult();
    
    boolean isComplete();
    
    boolean join() throws AbortException;
    
    boolean join(long timeout, TimeUnit unit) throws AbortException;
}
