package org.cyclopsgroup.caff.token;

public class EscapingValueTokenizer
    implements ValueTokenizer
{
    private final char escaper;

    public EscapingValueTokenizer( char escaper )
    {
        this.escaper = escaper;
    }

    public EscapingValueTokenizer()
    {
        this( '\\' );
    }

    public void parse( CharSequence input, TokenEventHandler handler )
    {
        // TODO Auto-generated method stub

    }

}
