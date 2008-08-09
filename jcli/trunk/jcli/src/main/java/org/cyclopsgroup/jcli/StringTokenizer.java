package org.cyclopsgroup.jcli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Tokenizer of argument string
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class StringTokenizer
{
    /**
     * Event handler of token event
     */
    public static interface TokenEventHandler
    {
        /**
         * Called when a complete word is found
         * 
         * @param word Value of word
         * @param start Starting position of word in original input
         * @param end End position of word in original input
         * @param terminated True if word is explicitly terminated
         */
        void handleWordEvent( String word, int start, int end, boolean terminated );
    }

    /**
     * Escape given string value based on same rule used for parsing arguments
     * 
     * @param value Value of argument
     * @return Escaped result
     */
    public abstract String escape( String value );

    /**
     * Find out list of arguments out of given string
     * 
     * @param input Full input string
     * @return List of arguments
     */
    public List<String> parse( String input )
    {
        if ( StringUtils.isEmpty( input ) )
        {
            return Collections.emptyList();
        }
        final List<String> results = new ArrayList<String>();
        parse( input, new TokenEventHandler()
        {
            public void handleWordEvent( String word, int start, int end, boolean terminated )
            {
                results.add( word );
            }
        } );
        return results;
    }

    /**
     * Parse string using a given token event handler
     * 
     * @param input Input string
     * @param handler Event handler
     */
    public abstract void parse( String input, TokenEventHandler handler );
}
