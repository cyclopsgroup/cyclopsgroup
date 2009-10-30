package org.cyclopsgroup.caff.token;

/**
 * An implementation that escapes special character with an escape character
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class EscapingValueTokenizer
    implements ValueTokenizer
{
    private final char escaper;

    /**
     * Default constructor sets escape character with back slash
     */
    public EscapingValueTokenizer()
    {
        this( '\\' );
    }

    /**
     * @param escaper Given escape character
     */
    public EscapingValueTokenizer( char escaper )
    {
        this.escaper = escaper;
    }

    /**
     * @return Escape character
     */
    public final char getEscaper()
    {
        return escaper;
    }

    /**
     * @inheritDoc
     */
    public void parse( CharSequence input, TokenEventHandler handler )
    {

    }
}
