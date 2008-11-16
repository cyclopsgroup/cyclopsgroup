package org.cyclopsgroup.waterview.impl.velocity;

import java.util.List;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.Validate;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

public class VelocityEngineBuilder
{
    private final ExtendedProperties props;

    private int sequence = 0;

    public VelocityEngineBuilder()
    {
        props = new ExtendedProperties();
        props.setProperty( "input.encoding", "UTF-8" );
    }

    public VelocityEngineBuilder addDeepClassPathResourceLoader( String prefix )
    {
        Validate.notNull( prefix, "Prefix can't be NULL" );
        sequence++;
        String loaderId = "loader" + sequence;
        props.addProperty( "resource.loader", loaderId );
        props.addProperty( loaderId + ".resource.loader.class", DeepClassPathResourceLoader.class.getName() );

        String path = prefix.replace( '.', '/' );
        if ( !path.endsWith( "/" ) )
        {
            path += "/";
        }
        props.addProperty( loaderId + ".resource.loader.prefix", path );
        return this;
    }

    public VelocityEngineBuilder addDeepClassPathResourceLoaders( List<String> prefixNames )
    {
        for ( String prefix : prefixNames )
        {
            addDeepClassPathResourceLoader( prefix );
        }
        return this;
    }

    public VelocityEngineBuilder addFileSystemResourceLoader( String path )
    {
        sequence++;
        String loaderId = "loader" + sequence;
        props.addProperty( "resource.loader", loaderId );
        props.addProperty( loaderId + ".resource.loader.class", FileResourceLoader.class.getName() );
        props.addProperty( loaderId + ".resource.loader.path", path );
        return this;
    }

    public VelocityEngineBuilder addProperty( String name, String value )
    {
        props.addProperty( name, value );
        return this;
    }

    public VelocityEngine newEngine()
    {
        VelocityEngine engine = new VelocityEngine();
        engine.setExtendedProperties( props );
        engine.setProperty( "runtime.log.logsystem", new JclLogChute() );
        try
        {
            engine.init();
        }
        catch ( RuntimeException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new RuntimeException( "Velocity engine intialization failed", e );
        }
        return engine;
    }

    public VelocityEngineBuilder setProperty( String name, String value )
    {
        props.setProperty( name, value );
        return this;
    }
}
