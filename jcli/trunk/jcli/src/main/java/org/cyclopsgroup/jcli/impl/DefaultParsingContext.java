package org.cyclopsgroup.jcli.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cyclopsgroup.jcli.spi.Cli;
import org.cyclopsgroup.jcli.spi.Option;
import org.cyclopsgroup.jcli.spi.ParsingContext;

class DefaultParsingContext<T>
    implements ParsingContext
{
    private final List<AnnotationOption> options;

    private final AnnotationCli cli;

    private final Map<String, Reference<T>> referenceMap;

    /**
     * @param beanType Type of bean
     * @param referenceMap Map of references
     * @param options List options
     * @param cli Command line model
     */
    DefaultParsingContext( Class<T> beanType, Map<String, Reference<T>> referenceMap, List<AnnotationOption> options,
                           AnnotationCli cli )
    {
        this.options = options;
        this.referenceMap = referenceMap;
        this.cli = cli;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Cli cli()
    {
        return cli;
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

    /**
     * @inheritDoc
     */
    @Override
    public List<Option> options()
    {
        return Arrays.asList( options.toArray( new Option[0] ) );
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
}