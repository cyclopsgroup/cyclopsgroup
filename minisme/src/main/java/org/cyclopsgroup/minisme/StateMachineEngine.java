package org.cyclopsgroup.minisme;

import java.util.Set;

public interface StateMachineEngine
{
    ExecutionContext getExecution( String executionId );

    void signal( String executionId, String signalName, String description, Object... arguments );

    ExecutionContext startExecution( String executionId, Set<String> tags, String description );

    void forceTerminate( String executionId, String description );
}
