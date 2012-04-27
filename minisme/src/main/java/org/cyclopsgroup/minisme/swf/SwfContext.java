package org.cyclopsgroup.minisme.swf;

import org.cyclopsgroup.minisme.provider.StateMachineDefinitionManager;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;

public class SwfContext
{
    private AmazonSimpleWorkflow service;

    private String domain;

    private StateMachineDefinitionManager definitionManager;

    public final AmazonSimpleWorkflow getService()
    {
        return service;
    }

    public final void setService( AmazonSimpleWorkflow service )
    {
        this.service = service;
    }

    public final String getDomain()
    {
        return domain;
    }

    public final void setDomain( String domain )
    {
        this.domain = domain;
    }

    public final StateMachineDefinitionManager getDefinitionManager()
    {
        return definitionManager;
    }

    public final void setDefinitionManager( StateMachineDefinitionManager definitionManager )
    {
        this.definitionManager = definitionManager;
    }
}
