package org.cyclopsgroup.waterview.impl.valve;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.WebModule;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

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
        WebContext wc = context.getWebContext();
        for ( String action : context.getActions() )
        {
            WebModule module = moduleResolver.findModule( action );
            module.render( wc );
        }
    }
}
