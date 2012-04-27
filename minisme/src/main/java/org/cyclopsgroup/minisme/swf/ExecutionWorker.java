package org.cyclopsgroup.minisme.swf;

import org.cyclopsgroup.minisme.provider.StateMachineDefinitionManager;

import com.amazonaws.services.simpleworkflow.model.ActivityTask;
import com.amazonaws.services.simpleworkflow.model.PollForActivityTaskRequest;
import com.amazonaws.services.simpleworkflow.model.RespondActivityTaskCompletedRequest;
import com.amazonaws.services.simpleworkflow.model.TaskList;

public class ExecutionWorker
    extends AbstractBaseWorker
{
    private final StateMachineDefinitionManager definitionManager;

    private final TaskList taskList;

    public ExecutionWorker( SwfContext context )
    {
        super( context.getService(), context.getDomain(), "execution" );
        this.definitionManager = context.getDefinitionManager();
        this.taskList = new TaskList().withName( getTaskListToPoll() );
    }

    /**
     * @inheritDoc
     */
    @Override
    boolean pollAndProcessSingleTask()
    {
        ActivityTask task =
            getService().pollForActivityTask( new PollForActivityTaskRequest().withDomain( domain ).withTaskList( taskList ).withIdentity( getIdentity() ) );
        if ( task.getActivityId() == null || task.getActivityType() == null )
        {
            return false;
        }
        getService().respondActivityTaskCompleted( new RespondActivityTaskCompletedRequest().withTaskToken( task.getTaskToken() ) );
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected String getPollThreadNamePrefix()
    {
        return "minisme-exec-";
    }

}
