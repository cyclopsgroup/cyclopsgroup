package org.cyclopsgroup.streammarker.spring;

import java.io.IOException;

import org.cyclopsgroup.streammarker.Marker;
import org.cyclopsgroup.streammarker.MarkerBuilder;
import org.springframework.beans.factory.FactoryBean;

public class SpringMarkerFactoryBean
    implements FactoryBean<Marker>
{
    private MarkerBuilder builder;

    private boolean singleton = true;

    /**
     * @inheritDoc
     */
    @Override
    public Marker getObject()
        throws IOException
    {
        if ( builder == null )
        {
            throw new IllegalStateException( "Builder is not configured yet" );
        }
        return builder.build();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Class<Marker> getObjectType()
    {
        return Marker.class;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isSingleton()
    {
        return singleton;
    }

    /**
     * @param builder Builder to set
     */
    public final void setBuilder( MarkerBuilder builder )
    {
        this.builder = builder;
    }

    public final void setSingleton( boolean singleton )
    {
        this.singleton = singleton;
    }
}
