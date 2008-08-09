package org.cyclopsgroup.jcli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Tokenizer based escape character
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class EscapedStringTokenizer
    extends StringTokenizer
{
    private enum ProcessingState
    {
        /**
         * Followd by escape character
         */
        ESCAPING,
        /**
         * Ready for new word
         */
        READY,
        /**
         * Appending a word
         */
        WORD;
    }

    private final char delimiter;

    private final String delimiterString;

    private final char escaper;

    private final String replacementString;

    /**
     * Default constructor using space as delimiter and back slash as escaper
     */
    public EscapedStringTokenizer()
    {
        this( ' ', '\\' );
    }

    /**
     * @param delimiter
     * @param escaper
     */
    public EscapedStringTokenizer( char delimiter, char escaper )
    {
        Validate.isTrue( delimiter != escaper, "Delimiter can't be the same as escaper" );
        this.delimiter = delimiter;
        this.escaper = escaper;
        this.delimiterString = "" + delimiter;
        this.replacementString = escaper + delimiterString;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String escape( String value )
    {
        if ( value == null || value.indexOf( delimiter ) == -1 )
        {
            return value;
        }
        return value.replace( delimiterString, replacementString );
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> parse( String input )
    {
        if ( StringUtils.isEmpty( input ) )
        {
            return Collections.emptyList();
        }
        List<String> results = new ArrayList<String>();
        ProcessingState state = ProcessingState.READY;
        StringBuilder word = null;
        for ( int i = 0; i < input.length(); i++ )
        {
            char c = input.charAt( i );
            switch ( state )
            {
                case READY:
                    if ( c == delimiter )
                    {
                        continue;
                    }
                    else
                    {
                        word = new StringBuilder();
                        if ( c == escaper )
                        {
                            state = ProcessingState.ESCAPING;
                        }
                        else
                        {
                            state = ProcessingState.WORD;
                            word.append( c );
                        }
                    }
                    break;
                case WORD:
                    if ( c == delimiter )
                    {
                        state = ProcessingState.READY;
                        results.add( word.toString() );
                        word = null;
                    }
                    else if ( c == escaper )
                    {
                        state = ProcessingState.ESCAPING;
                    }
                    else
                    {
                        word.append( c );
                    }
                    break;
                case ESCAPING:
                    state = ProcessingState.WORD;
                    word.append( c );
            }
        }
        if ( word != null )
        {
            results.add( word.toString() );
        }
        return results;
    }
}
