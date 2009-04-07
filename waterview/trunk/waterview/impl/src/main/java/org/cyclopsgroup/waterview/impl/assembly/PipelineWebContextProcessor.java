package org.cyclopsgroup.waterview.impl.assembly;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.pipeline.Pipeline;
import org.cyclopsgroup.waterview.spi.Valve;

/**
 * WebContextProcessor implementation based on a pipeline
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class PipelineWebContextProcessor
    implements WebContextProcessor
{
    private final Pipeline pipeline;
    
    /**
     * @param valves Give valves to construct a pipeline
     */
    public PipelineWebContextProcessor(List<Valve> valves)
    {
        this(new Pipeline(valves));
    }
    
    /**
     * @param pipeline Given pipeline
     */
    public PipelineWebContextProcessor(Pipeline pipeline)
    {
        Validate.notNull(pipeline, "Pipeline can't be NULL");
        this.pipeline = pipeline; 
    }

    /**
     * @inheritDoc
     */
    @Override
    public void process( WebContext context )
        throws IOException
    {
        pipeline.invoke( context );
    }

}
