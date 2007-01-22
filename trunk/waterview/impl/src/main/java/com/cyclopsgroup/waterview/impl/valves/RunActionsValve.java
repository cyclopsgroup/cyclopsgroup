package com.cyclopsgroup.waterview.impl.valves;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.ServiceManager;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.ResourceRegistry;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

public class RunActionsValve
    implements Valve
{
    private class AC
        implements ActionContext
    {
        public void addMessage( String message )
        {
            // TODO Auto-generated method stub

        }

        public void error( String inputName, String errorMessage )
        {

        }

        public void fail()
        {
            throw new ActionError( null, null );
        }

        public void fail( String errorMessage )
        {
            throw new ActionError( errorMessage, null );
        }

        public void fail( String errorMessage, Throwable throwable )
        {
            throw new ActionError( errorMessage, throwable );
        }

        public void fail( Throwable throwable )
        {
            throw new ActionError( null, throwable );
        }
    }

    private class ActionError
        extends Error
    {
        private String message;

        private Throwable throwable;

        private ActionError( String message, Throwable throwable )
        {
            this.message = message;
            this.throwable = throwable;
        }
    }

    private ResourceRegistry resourceRegistry;

    private ServiceManager serviceManager;

    public RunActionsValve( ResourceRegistry resourceRegistry )
    {
        this.resourceRegistry = resourceRegistry;
    }

    public ResourceRegistry getResourceRegistry()
    {
        return resourceRegistry;
    }

    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        if ( data.getActionQueue().isEmpty() )
        {
            context.invokeNextValve( data );
            return;
        }
        while ( !data.getActionQueue().isEmpty() )
        {
            String actionName = data.getActionQueue().remove();
            try
            {
                String className = resourceRegistry.getFullClassName( actionName );
                Action action = (Action) Class.forName( className ).newInstance();
                action.setServiceManager( getServiceManager() );
                action.execute( data, new AC() );
            }
            catch ( ActionError e )
            {
                context.stop();
                break;
            }
            catch ( Exception e )
            {

            }
        }
    }

    public void setServiceManager( ServiceManager serviceManager )
    {
        this.serviceManager = serviceManager;
    }
}