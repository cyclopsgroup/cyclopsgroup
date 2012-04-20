package org.cyclopsgroup.minisme;

import java.util.Set;

public interface ExecutionContext
{
    String getExecutionId();

    String getIdentifier();

    String getState();

    Set<String> getTags();
}
