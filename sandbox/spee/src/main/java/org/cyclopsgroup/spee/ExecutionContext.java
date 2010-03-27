package org.cyclopsgroup.spee;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ExecutionContext
{
    void abortWith( Serializable message );

    ExecutionEngine getEngine();

    ExecutionEngineContext getEngineContext();

    String getId();

    Map<String, Serializable> getVariables();

    void notifyWith( Notification notification );

    void notifyWith( Notification notification, long timeToLive, TimeUnit unit );

    <T extends Serializable> FlowFuture<T> startFlow( Flow<T> flow );

    Notification waitFor( Serializable condition )
        throws AbortException;

    Notification waitFor( Serializable condition, long timeout, TimeUnit unit )
        throws AbortException;

    Notification waitFor( List<Serializable> conditions )
        throws AbortException;

    Notification waitFor( List<Serializable> conditions, long timeout, TimeUnit unit )
        throws AbortException;
}
