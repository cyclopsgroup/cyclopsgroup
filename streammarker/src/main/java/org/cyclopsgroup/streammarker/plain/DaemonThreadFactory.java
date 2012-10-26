package org.cyclopsgroup.streammarker.plain;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory
    implements ThreadFactory
{
    private final String threadName;

    public DaemonThreadFactory( String threadName )
    {
        this.threadName = threadName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Thread newThread( Runnable r )
    {
        Thread thread = new Thread( r );
        thread.setDaemon( true );
        thread.setName( threadName + "-" + r.hashCode() );
        return thread;
    }
}
