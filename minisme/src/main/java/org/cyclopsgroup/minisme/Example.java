package org.cyclopsgroup.minisme;

import java.util.concurrent.TimeUnit;

@StateMachine( "Example" )
public interface Example
{
    @State( StateType.START )
    String PENDING = "PENDING";

    @State
    String DENIED = "DENIED";

    @State
    String APPROVED = "APPROVED";

    @State
    String ONHOLD = "ONHOLD";

    @State( StateType.END )
    String CLOSED = "CLOSED";

    @SingleSelectionTransition( from = Example.PENDING, selections = {
        @SingleSelection( on = "true", to = Example.APPROVED ), @SingleSelection( on = "false", to = Example.DENIED ) } )
    @SignalTrigger( name = "approve" )
    boolean approveDocument( @ContextParam( ContextParamType.IDENTIFIER )
                             String documentId, @ContextParam( ContextParamType.INPUT )
                             Object decision );

    @DirectTransition( from = Example.PENDING, to = Example.ONHOLD )
    @TimerTrigger( delay = 1, unit = TimeUnit.HOURS )
    void putOnHold( @ContextParam( ContextParamType.IDENTIFIER )
                    String documentId );
}
