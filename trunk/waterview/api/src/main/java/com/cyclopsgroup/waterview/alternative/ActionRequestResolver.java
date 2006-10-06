package com.cyclopsgroup.waterview.alternative;

import java.io.IOException;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.ExecutionException;
import com.cyclopsgroup.waterview.spi.RunDataSpi;

/**
 * 
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ActionRequestResolver
    extends AbstractExtensionBasedRequestResolver
{
    private ClassLoader actionClassLoader = getClass().getClassLoader();

    public ActionRequestResolver( String actionExtension )
    {
        super( actionExtension );
    }

    private class InternalActionContext
        implements ActionContext
    {

        public void addMessage( String message )
        {
            // TODO Auto-generated method stub

        }

        public void error( String inputName, String errorMessage )
        {
            // TODO Auto-generated method stub

        }

        public void fail()
        {
            // TODO Auto-generated method stub

        }

        public void fail( String errorMessage )
        {
            // TODO Auto-generated method stub

        }

        public void fail( String errorMessage, Throwable throwable )
        {
            // TODO Auto-generated method stub

        }

        public void fail( Throwable throwable )
        {
            // TODO Auto-generated method stub

        }

    }

    /**
     * @see com.cyclopsgroup.waterview.spi.RequestResolver#resolveRequest(com.cyclopsgroup.waterview.spi.RunDataSpi, com.cyclopsgroup.waterview.spi.RunDataSpi.Request)
     */
    public void resolveRequest( RunDataSpi data, RunDataSpi.Request request )
        throws ExecutionException, IOException
    {
        String packageName = data.getWaterview().getPackageMap().get( request.getPackageAlias() );
        if ( packageName == null )
        {
            throw new IllegalArgumentException( "Request contains unknown package alias :" + request.getPackageAlias() );
        }

        String requestPath = request.getRequestPath();
        String className = request.getRequestPath().substring( 0, requestPath.length() - getExtension().length() - 1 );
        className = packageName + "." + className.replace( '/', '.' );

        try
        {
            Action action = (Action) getActionClassLoader().loadClass( className ).newInstance();
            action.execute( data, new InternalActionContext() );
        }
        catch ( ClassNotFoundException e )
        {
            //Ignore if class is not found
        }
        catch ( ExecutionException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ExecutionException( "Running action class " + className + " failed", e, data );
        }
    }

    public ClassLoader getActionClassLoader()
    {
        return actionClassLoader;
    }

    public void setActionClassLoader( ClassLoader actionClassLoader )
    {
        this.actionClassLoader = actionClassLoader;
    }
}
