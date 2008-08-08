package org.cyclopsgroup.jcli.jline;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.jcli.spi.CliDefinition;
import org.cyclopsgroup.jcli.spi.OptionDefinition;

class ArgumentsInspector
{
    private final CliDefinition cli;

    private OptionDefinition currentOption;

    private String currentValue;

    private final Set<OptionDefinition> remainingOptions;

    private ArgumentsInspectorState state = ArgumentsInspectorState.READY;

    ArgumentsInspector( CliDefinition cli )
    {
        this.cli = cli;
        remainingOptions = new HashSet<OptionDefinition>( cli.getOptions().values() );
    }

    void consume( String argument )
    {
        if ( argument.startsWith( "--" ) )
        {
            state = ArgumentsInspectorState.LONG_OPTION;
        }
        else if ( argument.startsWith( "-" ) )
        {
            state = ArgumentsInspectorState.OPTION;
        }
        else
        {
            switch ( state )
            {
                case READY:
                    state = ArgumentsInspectorState.ARGUMENT;
                    break;
                case OPTION:
                    currentOption = findOptionByName( currentValue );
                case LONG_OPTION:
                    if ( state == ArgumentsInspectorState.LONG_OPTION )
                    {
                        currentOption = findOptionByLongName( currentValue );
                    }
                    if ( currentOption != null && !currentOption.isMultiValue() )
                    {
                        remainingOptions.remove( currentOption );
                    }
                    if ( currentOption == null || currentOption.isFlag() )
                    {
                        state = ArgumentsInspectorState.ARGUMENT;
                    }
                    else
                    {
                        state = ArgumentsInspectorState.OPTION_VALUE;
                    }
                    break;
                case OPTION_VALUE:
                case ARGUMENT:
                    state = ArgumentsInspectorState.ARGUMENT;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
        currentValue = argument;
    }

    void end()
    {
        switch ( state )
        {
            case OPTION:
                currentOption = findOptionByName( currentValue );
            case LONG_OPTION:
                if ( state == ArgumentsInspectorState.LONG_OPTION )
                {
                    currentOption = findOptionByLongName( currentValue );
                }
                if ( currentOption != null && !currentOption.isMultiValue() )
                {
                    remainingOptions.remove( currentOption );
                }
                if ( currentOption == null || currentOption.isFlag() )
                {
                    state = ArgumentsInspectorState.ARGUMENT;
                }
                else
                {
                    state = ArgumentsInspectorState.OPTION_VALUE;
                }
                break;
            default:
                state = ArgumentsInspectorState.READY;
        }
        currentValue = null;
    }

    private OptionDefinition findOptionByLongName( String longName )
    {
        for ( OptionDefinition o : cli.getOptions().values() )
        {
            if ( o.getOption().longName() != null && StringUtils.equals( "--" + o.getOption().longName(), longName ) )
            {
                return o;
            }
        }
        return null;
    }

    private OptionDefinition findOptionByName( String name )
    {
        return cli.getOptions().get( name.substring( 1 ) );
    }

    OptionDefinition getCurrentOption()
    {
        return currentOption;
    }

    String getCurrentValue()
    {
        return currentValue;
    }

    Set<OptionDefinition> getRemainingOptions()
    {
        return Collections.unmodifiableSet( remainingOptions );
    }

    ArgumentsInspectorState getState()
    {
        return state;
    }
}
