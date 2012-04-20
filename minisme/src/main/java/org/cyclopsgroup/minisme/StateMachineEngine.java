package org.cyclopsgroup.minisme;

import java.util.Set;

public interface StateMachineEngine
{
    ExecutionContext getExecution( String executionId );

    void signal( String executionId, String signalName, Object input );

    ExecutionContext startExecution( String identifier, Set<String> tags );

    void forceTerminate( String executionId );
}
