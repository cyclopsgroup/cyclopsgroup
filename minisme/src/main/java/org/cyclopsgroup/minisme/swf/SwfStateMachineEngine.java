package org.cyclopsgroup.minisme.swf;

import java.util.Set;

import org.cyclopsgroup.minisme.ExecutionContext;
import org.cyclopsgroup.minisme.StateMachineEngine;
import org.cyclopsgroup.minisme.provider.SimpleExecutionContext;
import org.cyclopsgroup.minisme.provider.StateMachineDefinition;

import com.amazonaws.services.simpleworkflow.model.Run;
import com.amazonaws.services.simpleworkflow.model.StartWorkflowExecutionRequest;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

public class SwfStateMachineEngine
    implements StateMachineEngine
{
    private final SwfContext context;

    public SwfStateMachineEngine( SwfContext context )
    {
        this.context = context;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutionContext getExecution( String executionId )
    {
        // TODO Auto-generated method stub
        return null;

    }

    /**
     * @inheritDoc
     */
    @Override
    public void signal( String executionId, String signalName, Object input )
    {

    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutionContext startExecution( String workflowType, String identifier, Set<String> tags )
    {
        StateMachineDefinition definition = context.getDefinitionManager().getDefinition( workflowType );
        WorkflowType type = new WorkflowType().withName( definition.getName() ).withVersion( "1.0" );
        Run run =
            context.getService().startWorkflowExecution( new StartWorkflowExecutionRequest().withDomain( context.getDomain() ).withWorkflowType( type ).withWorkflowId( identifier ).withTagList( tags ) );
        return new SimpleExecutionContext( run.getRunId(), identifier, tags, definition.getStartState() );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void forceTerminate( String executionId )
    {
    }
}
