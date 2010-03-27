package org.cyclopsgroup.spee.mem;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.cyclopsgroup.spee.AbortException;
import org.cyclopsgroup.spee.ExecutionContext;
import org.cyclopsgroup.spee.ExecutionEngine;
import org.cyclopsgroup.spee.ExecutionEngineContext;
import org.cyclopsgroup.spee.Flow;
import org.cyclopsgroup.spee.FlowFuture;
import org.cyclopsgroup.spee.Notification;
import org.cyclopsgroup.spee.spi.DependencyManager;
import org.cyclopsgroup.spee.spi.storage.StorageManager;

public class InMemoryExecutionContext
    implements ExecutionContext
{
    private final ExecutionEngineContext engineContext = new ExecutionEngineContext()
    {
        /**
         * @inheritDoc
         */
        @Override
        public <T> T findComponent( String name, Class<T> type )
        {
            return dependencies.findComponent( name, type );
        }

        /**
         * @inheritDoc
         */
        @Override
        public <T> T findComponent( Class<T> type )
        {
            return dependencies.findComponent( type );
        }

    };

    private final DependencyManager dependencies;

    private final ExecutionEngine engine;

    private final String executionId;

    private final StorageManager storage;

    private CountDownLatch latch;

    public InMemoryExecutionContext( String executionId, ExecutionEngine engine, DependencyManager dependencies,
                                     StorageManager storage )
    {
        this.executionId = executionId;
        this.engine = engine;
        this.dependencies = dependencies;
        this.storage = storage;
    }

    @Override
    public void abortWith( Serializable message )
    {
        // TODO Auto-generated method stub

    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutionEngine getEngine()
    {
        return engine;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutionEngineContext getEngineContext()
    {
        return engineContext;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getId()
    {
        return executionId;
    }

    @Override
    public Map<String, Serializable> getVariables()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void notifyWith( Notification notification )
    {
        storage.addNotification( executionId, notification, 0, TimeUnit.SECONDS );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void notifyWith( Notification notification, long timeToLive, TimeUnit unit )
    {
        storage.addNotification( executionId, notification, timeToLive, unit );

    }

    @Override
    public <T extends Serializable> FlowFuture<T> startFlow( Flow<T> flow )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Notification waitFor( List<Serializable> conditions, long timeout, TimeUnit unit )
        throws AbortException
    {
        storage.setWaitingFor( executionId, conditions, timeout, unit );
        // FIXME It doens't work
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Notification waitFor( List<Serializable> conditions )
        throws AbortException
    {
        return waitFor( conditions, 0, TimeUnit.SECONDS );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Notification waitFor( Serializable condition, long timeout, TimeUnit unit )
        throws AbortException
    {
        return waitFor( Collections.singletonList( condition ), timeout, unit );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Notification waitFor( Serializable condition )
        throws AbortException
    {
        return waitFor( condition, 0, TimeUnit.SECONDS );
    }
}
