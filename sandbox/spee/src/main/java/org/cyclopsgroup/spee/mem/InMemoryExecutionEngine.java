package org.cyclopsgroup.spee.mem;

import org.cyclopsgroup.spee.ExecutionContext;
import org.cyclopsgroup.spee.ExecutionEngine;
import org.cyclopsgroup.spee.spi.storage.StorageManager;

public class InMemoryExecutionEngine
    implements ExecutionEngine
{
    private final StorageManager storage;

    public InMemoryExecutionEngine( StorageManager storage )
    {
        this.storage = storage;
    }

    @Override
    public ExecutionContext createExecution( String executionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExecutionContext findExecution( String executionId )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
