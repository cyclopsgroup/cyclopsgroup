package org.cyclopsgroup.waterview.impl.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

/**
 * Internal implementation of velocity specific logging
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class JclLogChute
    implements LogChute
{
    private final Log log = LogFactory.getLog( JclLogChute.class );

    /**
     * @inheritDoc
     */
    @Override
    public void init( RuntimeServices runtimeServices )
        throws Exception
    {
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isLevelEnabled( int level )
    {
        switch ( level )
        {
            case LogChute.DEBUG_ID:
                return log.isDebugEnabled();
            case LogChute.TRACE_ID:
                return log.isTraceEnabled();
            case LogChute.ERROR_ID:
                return log.isErrorEnabled();
            case LogChute.WARN_ID:
                return log.isWarnEnabled();
            case LogChute.INFO_ID:
            default:
                return log.isInfoEnabled();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void log( int level, String message )
    {
        log( level, message, null );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void log( int level, String message, Throwable e )
    {
        switch ( level )
        {
            case LogChute.DEBUG_ID:
                log.debug( message, e );
                return;
            case LogChute.TRACE_ID:
                log.trace( message, e );
                return;
            case LogChute.ERROR_ID:
                log.error( message, e );
                return;
            case LogChute.WARN_ID:
                log.warn( message, e );
                return;
            case LogChute.INFO_ID:
            default:
                log.info( message, e );
        }
    }

}
