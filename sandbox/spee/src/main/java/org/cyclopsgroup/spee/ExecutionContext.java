package org.cyclopsgroup.spee;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ExecutionContext
{
    String getId();

    void abortWith(Object message);

    EngineContext getEngineContext();

    Map<String, Object> getVariables();

    void notifyWith(Object message);

    void notifyWith(Object message, long timeToLive, TimeUnit unit);

    <T> T waitFor(Predicate<T> predicate) throws AbortException;

    <T> T waitFor(Predicate<T> predicate, long timeout, TimeUnit unit) throws AbortException;

    <T> FlowFuture<T> startFlow(Flow<T> flow);
}
