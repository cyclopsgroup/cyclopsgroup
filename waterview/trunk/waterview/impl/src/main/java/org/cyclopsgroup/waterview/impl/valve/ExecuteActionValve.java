package org.cyclopsgroup.waterview.impl.valve;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.WebModule;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;

/**
 * Valve that execute an action
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ExecuteActionValve
    implements Valve
{
    private final ModuleResolver moduleResolver;

    /**
     * @param moduleResolver Module resolver to find action
     */
    public ExecuteActionValve( ModuleResolver moduleResolver )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        this.moduleResolver = moduleResolver;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
    {
        WebModule action = moduleResolver.findModule( "name" );
        action.render( context.getWebContext() );
    }
}
