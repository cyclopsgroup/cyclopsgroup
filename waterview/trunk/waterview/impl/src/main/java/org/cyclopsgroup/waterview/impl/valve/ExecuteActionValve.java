package org.cyclopsgroup.waterview.impl.valve;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.ActionModule;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;

public class ExecuteActionValve
    implements Valve
{
    private final ModuleResolver moduleResolver;

    public ExecuteActionValve( ModuleResolver moduleResolver )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        this.moduleResolver = moduleResolver;
    }

    @Override
    public void invoke( ValveContext context )
    {
        ActionModule action = moduleResolver.findActionModule( "name" );
        context.setActionRedirection( action.executeAction( context.getWebContext() ) );
    }
}
