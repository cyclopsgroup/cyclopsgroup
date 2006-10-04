package com.cyclopsgroup.waterview.alternative;

import com.cyclopsgroup.waterview.RunData;

public class SinglePipelineWaterview
    extends AbstractPipelineBasedWaterview
{
    private final Pipeline pipeline;

    public SinglePipelineWaterview( Pipeline pipeline )
    {
        this.pipeline = pipeline;
    }

    @Override
    protected Pipeline getPipeline( RunData data )
    {
        return pipeline;
    }

    public Pipeline getPipeline()
    {
        return pipeline;
    }
}
