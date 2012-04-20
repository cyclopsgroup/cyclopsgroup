package org.cyclopsgroup.minisme.swf;

import java.util.Set;

import org.cyclopsgroup.minisme.ExecutionContext;
import org.cyclopsgroup.minisme.StateMachineEngine;
import org.cyclopsgroup.minisme.provider.SimpleExecutionContext;
import org.cyclopsgroup.minisme.provider.StateMachineDefinition;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.model.Run;
import com.amazonaws.services.simpleworkflow.model.StartWorkflowExecutionRequest;

public class SwfStateMachineEngine
    implements StateMachineEngine
{
    private final AmazonSimpleWorkflow workflow;

    private final String domain;

    private final StateMachineDefinition definition;

    public SwfStateMachineEngine( Object stateMachine, AmazonSimpleWorkflow workflow, String domain )
    {
        definition = new StateMachineDefinition( stateMachine );
        this.workflow = workflow;
        this.domain = domain;
    }

    @Override
    public ExecutionContext getExecution( String executionId )
    {
        // TODO Auto-generated method stub
        return null;

    }

    @Override
    public void signal( String executionId, String signalName, Object input )
    {

    }

    @Override
    public ExecutionContext startExecution( String identifier, Set<String> tags )
    {
        Run run =
            workflow.startWorkflowExecution( new StartWorkflowExecutionRequest().withDomain( domain ).withWorkflowId( identifier ).withTagList( tags ) );
        return new SimpleExecutionContext( run.getRunId(), identifier, tags, definition.getStartState() );
    }

    @Override
    public void forceTerminate( String executionId )
    {
        // TODO Auto-generated method stub

    }
}
