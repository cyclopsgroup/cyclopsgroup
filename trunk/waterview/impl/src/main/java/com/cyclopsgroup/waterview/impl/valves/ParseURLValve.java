package com.cyclopsgroup.waterview.impl.valves;

import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

public class ParseURLValve
    implements Valve
{
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        context.invokeNextValve( data );
    }
}