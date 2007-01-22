package com.cyclopsgroup.waterview.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public final class Pipeline
{
    private final List<Valve> valves = new Vector<Valve>();

    public void addValve( Valve valve )
    {
        valves.add( valve );
    }

    /**
     * Execute the pipeline
     * 
     * @param runDataSpi
     * @throws ExecutionException
     * @throws IOException
     */
    public void run( RunDataSpi runDataSpi )
        throws Exception
    {
        if ( valves.isEmpty() )
        {
            return;
        }

        final Iterator<Valve> it = valves.iterator();
        PipelineContext pc = new PipelineContext()
        {
            private boolean stopped;

            public void invokeNextValve( RunDataSpi data )
                throws Exception
            {
                if ( stopped )
                {
                    return;
                }

                if ( it.hasNext() )
                {
                    Valve valve = it.next();
                    valve.invoke( data, this );
                }
            }

            public boolean isStopped()
            {
                return stopped;
            }

            public void stop()
            {
                stopped = true;
            }
        };
        pc.invokeNextValve( runDataSpi );
    }
}
