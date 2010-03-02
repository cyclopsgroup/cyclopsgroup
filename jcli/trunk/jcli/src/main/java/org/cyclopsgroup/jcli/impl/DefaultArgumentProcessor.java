package org.cyclopsgroup.jcli.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.jcli.ArgumentProcessor;
import org.cyclopsgroup.jcli.spi.CommandLine;
import org.cyclopsgroup.jcli.spi.CommandLineParser;
import org.cyclopsgroup.jcli.spi.Option;

class DefaultArgumentProcessor<T>
    extends ArgumentProcessor<T>
{
    static final String ARGUMENT_REFERNCE_NAME = "----arguments----";

    private final DefaultParsingContext<T> context;

    private final CommandLineParser parser;

    DefaultArgumentProcessor( Class<T> beanType, CommandLineParser parser )
    {
        this.parser = parser;
        context = new ParsingContextBuilder<T>( beanType ).build();
    }

    DefaultParsingContext<T> getContext()
    {
        return context;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void printHelp( PrintWriter out )
        throws IOException
    {
        out.println( "name:  " + context.cli().getName() );
        out.println( "usage: " + context.cli().getName() + "<OPTIONS>" + "[ARGUMENTS]" );
        if ( !StringUtils.isBlank( context.cli().getDescription() ) )
        {
            out.println( context.cli().getDescription() );
        }
        for ( Option option : context.options() )
        {
            out.println( String.format( " -%s --%s %s %s", option.getName(), option.getLongName(), option.isFlag() ? ""
                            : "<" + option.getDisplayName() + ">", option.getDescription() ) );
        }
        if ( !StringUtils.isBlank( context.cli().getNote() ) )
        {
            out.println( "note:  " + context.cli().getNote() );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void process( List<String> arguments, T bean )
    {
        CommandLine cli = parser.parse( arguments, context );
        Map<String, List<String>> multiValues = new HashMap<String, List<String>>();
        for ( CommandLine.OptionValue ov : cli.getOptionValues() )
        {
            Reference<T> ref = context.lookupReference( ov.name, !ov.shortName );
            if ( ref == null )
            {
                throw new AssertionError( "Option " + ov.name + " doesn't exist" );
            }
            if ( ref instanceof SingleValueReference<?> )
            {
                ( (SingleValueReference<T>) ref ).setValue( bean, ov.value );
                continue;
            }
            String optionName;
            if ( ov.shortName )
            {
                optionName = ov.name;
            }
            else
            {
                optionName = context.optionWithLongName( ov.name ).getName();
            }
            List<String> values = multiValues.get( optionName );
            if ( values == null )
            {
                values = new ArrayList<String>();
                multiValues.put( optionName, values );
            }
            values.add( ov.value );
        }
        for ( Map.Entry<String, List<String>> entry : multiValues.entrySet() )
        {
            MultiValueReference<T> ref = (MultiValueReference<T>) context.lookupReference( entry.getKey(), false );
            ref.setValues( bean, entry.getValue() );
        }
        Reference<T> ref = context.lookupReference( ARGUMENT_REFERNCE_NAME, false );
        if ( ref == null )
        {
            return;
        }
        if ( ref instanceof MultiValueReference<?> )
        {
            ( (MultiValueReference<T>) ref ).setValues( bean, cli.getArguments() );
        }
        else
        {
            String value = cli.getArguments().isEmpty() ? null : cli.getArguments().get( 0 );
            ( (SingleValueReference<T>) ref ).setValue( bean, value );
        }
    }
}