package org.cyclopsgroup.spee.spi.storage;

public interface StoredExecution
{
    String getExecutionId();

    StoredExecutionState getExecutionState();
}
