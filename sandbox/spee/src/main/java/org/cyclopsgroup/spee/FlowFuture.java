package org.cyclopsgroup.spee;

import java.util.concurrent.TimeUnit;

public interface FlowFuture<T>
{
    ExecutionContext getExecution();

    boolean isComplete();

    T getResult()
        throws AbortException;

    T getResult( long timeout, TimeUnit unit )
        throws AbortException;
}
