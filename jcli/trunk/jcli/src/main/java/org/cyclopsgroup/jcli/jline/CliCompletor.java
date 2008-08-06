package org.cyclopsgroup.jcli.jline;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import jline.Completor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.jcli.AutoCompletable;
import org.cyclopsgroup.jcli.QuotedStringTokenizer;
import org.cyclopsgroup.jcli.spi.CliDefinition;
import org.cyclopsgroup.jcli.spi.CliUtils;

/**
 * JLine completor implemented with JCli
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CliCompletor
    implements Completor
{
    private final CliDefinition cli;

    private final AutoCompletable completable;

    private final QuotedStringTokenizer tokenizer;

    /**
     * @param completable Entyped AutoCompletable implementation
     * @param tokenizer Tokenizer for argument parsing
     * @throws IntrospectionException
     */
    public CliCompletor( final AutoCompletable completable, final QuotedStringTokenizer tokenizer )
        throws IntrospectionException
    {
        Validate.notNull( completable, "AutoCompletable can't be NULL" );
        Validate.notNull( tokenizer, "String tokenizer can't be NULL" );
        this.completable = completable;
        cli = CliUtils.defineCli( completable.getClass() );
        this.tokenizer = tokenizer;
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    public int complete( final String command, final int cursor, final List suggestions )
    {

        ArgumentsInspector inspector = new ArgumentsInspector( cli );
        if ( StringUtils.isNotEmpty( command ) )
        {
            List<String> args = tokenizer.parse( command );
            for ( String arg : args )
            {
                inspector.consume( arg );
            }
            if ( command.charAt( command.length() - 1 ) == ' ' )
            {
                inspector.end();
            }
        }
        switch ( inspector.getState() )
        {
            case READY:
                break ;
            case OPTION:
                ;
            case LONG_OPTION:
            case OPTION_VALUE:
            case ARGUMENT:
        }
        return 0;
    }

    private List<String> suggestArguments( String partialArgument )
    {
        if ( partialArgument == null )
        {
            return completable.suggestArgument( null );
        }
        List<String> results = completable.suggestArgument( partialArgument );
        if ( results == null )
        {
            results = new ArrayList<String>();
            for ( String arg : completable.suggestArgument( null ) )
            {
                if ( arg.startsWith( partialArgument ) )
                {
                    results.add( arg );
                }
            }
        }
        return results;
    }
}
