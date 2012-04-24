package org.cyclopsgroup.minisme.swf;

import org.cyclopsgroup.minisme.provider.StateMachineDefinition;
import org.cyclopsgroup.minisme.swf.message.TransitionInput;
import org.cyclopsgroup.minisme.swf.message.TransitionOutput;

public class SwfAdapterActivities
    implements AdapterActivities
{
    private final StateMachineDefinition smd;

    public SwfAdapterActivities( Object stateMachine )
    {
        this.smd = new StateMachineDefinition( stateMachine );
    }

    public TransitionOutput transit( TransitionInput input )
    {
        TransitionOutput output = new TransitionOutput();

        return output;
    }
}
