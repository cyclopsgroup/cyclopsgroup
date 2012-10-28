package org.cyclopsgroup.minisme.swf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.minisme.provider.StateMachineDefinition;
import org.cyclopsgroup.minisme.provider.StateMachineDefinitionManager;

import com.amazonaws.services.simpleworkflow.model.DecisionTask;
import com.amazonaws.services.simpleworkflow.model.EventType;
import com.amazonaws.services.simpleworkflow.model.HistoryEvent;
import com.amazonaws.services.simpleworkflow.model.PollForDecisionTaskRequest;
import com.amazonaws.services.simpleworkflow.model.RespondDecisionTaskCompletedRequest;
import com.amazonaws.services.simpleworkflow.model.TaskList;

public class ControlWorker
    extends AbstractBaseWorker
{
    private static final Log LOG = LogFactory.getLog( ControlWorker.class );

    private final StateMachineDefinitionManager definitionManager;

    public ControlWorker( SwfContext context )
    {
        super( context.getService(), context.getDomain(), "control" );
        this.definitionManager = context.getDefinitionManager();
    }

    /**
     * @inheritDoc
     */
    @Override
    boolean pollAndProcessSingleTask()
    {
        TaskList taskList = new TaskList().withName( getTaskListToPoll() );
        PollForDecisionTaskRequest poll =
            new PollForDecisionTaskRequest().withDomain( domain ).withIdentity( getIdentity() ).withTaskList( taskList );
        poll.withReverseOrder( true ).withMaximumPageSize( 50 );

        DecisionTask task = service.pollForDecisionTask( poll );
        if ( task.getWorkflowExecution() == null || task.getWorkflowType() == null )
        {
            // Ignore empty task
            return true;
        }
        StateMachineDefinition definition = definitionManager.getDefinition( task.getWorkflowType().getName() );
        if ( definition == null )
        {
            LOG.warn( "Unexpected workflow type " + task.getWorkflowType() );
            return false;
        }

        RespondDecisionTaskCompletedRequest reply = null;

        for ( HistoryEvent event : task.getEvents() )
        {
            if ( event.getEventId() >= task.getStartedEventId() )
            {
                continue;
            }
            if ( event.getEventId() <= task.getPreviousStartedEventId() )
            {
                reply =
                    new RespondDecisionTaskCompletedRequest().withExecutionContext( "No significant event is found between decision start "
                        + task.getStartedEventId()
                        + " and  previous decision start "
                        + task.getPreviousStartedEventId() );
            }
            EventType type = EventType.valueOf( event.getEventType() );
            switch ( type )
            {
                case WorkflowExecutionStarted:

            }
        }

        if ( reply == null )
        {
            LOG.warn( "Decision maker didn't make decision" );
            return false;
        }
        reply.setTaskToken( task.getTaskToken() );
        service.respondDecisionTaskCompleted( reply );
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected String getPollThreadNamePrefix()
    {
        return "minisme-ctrl-";
    }
}