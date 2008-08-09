package org.cyclopsgroup.jcli;

import java.util.List;

/**
 * Tokenizer of argument string
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class StringTokenizer
{
    /**
     * Find out list of arguments out of given string
     * 
     * @param input Full input string
     * @return List of arguments
     */
    public abstract List<String> parse( String input );

    /**
     * Escape given string value based on same rule used for parsing arguments
     * 
     * @param value Value of argument
     * @return Escaped result
     */
    public abstract String escape( String value );
}
