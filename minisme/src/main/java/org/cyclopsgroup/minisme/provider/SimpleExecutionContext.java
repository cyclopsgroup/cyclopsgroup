package org.cyclopsgroup.minisme.provider;

import java.util.Set;

import org.cyclopsgroup.minisme.ExecutionContext;

public class SimpleExecutionContext
    implements ExecutionContext
{

    private final String executionId;

    private final String identifier;

    private final Set<String> tags;

    private final String state;

    public SimpleExecutionContext( String executionId, String identifier, Set<String> tags, String state )
    {
        this.executionId = executionId;
        this.identifier = identifier;
        this.tags = tags;
        this.state = state;
    }

    @Override
    public String getExecutionId()
    {
        return executionId;
    }

    @Override
    public String getIdentifier()
    {
        return identifier;
    }

    @Override
    public String getState()
    {
        return state;
    }

    @Override
    public Set<String> getTags()
    {
        return tags;
    }

}
