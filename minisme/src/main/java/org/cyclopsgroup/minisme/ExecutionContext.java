package org.cyclopsgroup.minisme;

import java.util.Map;
import java.util.Set;

public interface ExecutionContext
{
    Map<String, Object> getAttributes();

    String getExecutionId();

    String getId();

    String getState();

    Set<String> getTags();
}
