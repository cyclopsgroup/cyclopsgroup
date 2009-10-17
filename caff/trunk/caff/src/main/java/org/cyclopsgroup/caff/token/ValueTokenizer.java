package org.cyclopsgroup.caff.token;

/**
 * A parser that reads input and generates a series of {@link TokenEvent}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface ValueTokenizer
{
    /**
     * @param input Characters of input
     * @param handler Handler of token events
     */
    void parse( CharSequence input, TokenEventHandler handler );
}
