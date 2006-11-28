package com.cyclopsgroup.waterview.impl;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.alternative.AbstractPipelineBasedWaterview;
import com.cyclopsgroup.waterview.alternative.Pipeline;

public class MultiPipelineWaterview
    extends AbstractPipelineBasedWaterview
{
    private final Map<Pattern, Pipeline> pipelines = new Hashtable<Pattern, Pipeline>();

    private Pipeline defaultPipeline;

    public MultiPipelineWaterview( Pipeline defaultPipeline )
    {
        this.defaultPipeline = defaultPipeline;
    }

    public void addPipeline( String pattern, Pipeline pipeline )
        throws PatternSyntaxException
    {
        Pattern p = Pattern.compile( pattern );
        pipelines.put( p, pipeline );
    }

    @Override
    protected Pipeline getPipeline( final RunData data )
    {
        final String requestPath = data.getRequestPath();
        Pipeline pipeline = defaultPipeline;

        for ( Pattern p : pipelines.keySet() )
        {
            if ( p.matcher( requestPath ).matches() )
            {
                pipeline = pipelines.get( p );
                break;
            }
        }
        return pipeline;
    }
}
