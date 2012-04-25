package org.cyclopsgroup.minisme.swf;

import java.util.concurrent.TimeUnit;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.worker.GenericWorker;
import com.amazonaws.services.simpleworkflow.flow.worker.TaskPoller;

public abstract class AbstractBaseWorker
    extends GenericWorker
{
    private class Poller
        implements TaskPoller
    {
        /**
         * @inheritDoc
         */
        @Override
        public boolean pollAndProcessSingleTask()
            throws Exception
        {
            return AbstractBaseWorker.this.pollAndProcessSingleTask();
        }

        @Override
        public void shutdown()
        {
            shutdownNow();
        }

        @Override
        public void shutdownNow()
        {
        }

        @Override
        public boolean awaitTermination( long left, TimeUnit milliseconds )
            throws InterruptedException
        {
            return true;
        }
    }

    public AbstractBaseWorker( AmazonSimpleWorkflow service, String domain, String taskList )
    {
        super( service, domain, taskList );
    }

    /**
     * @inhericDoc
     */
    @Override
    public void registerTypesToPoll()
    {
    }

    abstract boolean pollAndProcessSingleTask()
        throws Exception;

    /**
     * @inheritDoc
     */
    @Override
    protected TaskPoller createPoller()
    {
        return new Poller();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void checkRequredProperties()
    {
    }
}
