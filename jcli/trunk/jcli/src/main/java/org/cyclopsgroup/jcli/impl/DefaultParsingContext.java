package org.cyclopsgroup.jcli.impl;

import java.util.List;
import java.util.Map;

import org.cyclopsgroup.jcli.spi.Option;
import org.cyclopsgroup.jcli.spi.ParsingContext;

class DefaultParsingContext<T>
    implements ParsingContext
{
    private final List<AnnotationOption> options;

    private final Map<String, Reference<T>> referenceMap;

    DefaultParsingContext( Class<T> beanType, Map<String, Reference<T>> referenceMap, List<AnnotationOption> options )
    {
        this.options = options;
        this.referenceMap = referenceMap;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Option optionWithLongName( String longName )
    {
        for ( Option o : options )
        {
            if ( o.getLongName().equals( longName ) )
            {
                return o;
            }
        }
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Option optionWithShortName( String shortName )
    {
        for ( Option o : options )
        {
            if ( o.getName().equals( shortName ) )
            {
                return o;
            }
        }
        return null;
    }

    Reference<T> lookupReference( String name, boolean isLongName )
    {
        if ( isLongName )
        {
            for ( Reference<T> r : referenceMap.values() )
            {
                if ( r.longName.equals( name ) )
                {
                    return r;
                }
            }
            return null;
        }
        return referenceMap.get( name );
    }
}