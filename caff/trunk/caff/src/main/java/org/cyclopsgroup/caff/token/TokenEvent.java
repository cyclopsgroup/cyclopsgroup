package org.cyclopsgroup.caff.token;

/**
 * A Java bean that stands for an event of token
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class TokenEvent
{
    private final int end;

    private boolean quoted;

    private final int start;

    private boolean terminated;

    private final String token;

    /**
     * Constructor that requires token, start positoion and end position
     *
     * @param token Value of token
     * @param start Zero based start position
     * @param end One based last character position
     */
    public TokenEvent( String token, int start, int end )
    {
        this.token = token;
        this.start = start;
        this.end = end;
    }

    /**
     * @return One based position of last character in token
     */
    public final int getEnd()
    {
        return end;
    }

    /**
     * @return Zero based start position of token
     */
    public final int getStart()
    {
        return start;
    }

    /**
     * @return Value of token
     */
    public final String getToken()
    {
        return token;
    }

    /**
     * @return True if token is explicitly quoted
     */
    public final boolean isQuoted()
    {
        return quoted;
    }

    /**
     * @return True if token is explicitly terminated
     */
    public final boolean isTerminated()
    {
        return terminated;
    }

    /**
     * @param quoted True if token is explicitly quoted
     */
    public final void setQuoted( boolean quoted )
    {
        this.quoted = quoted;
    }

    /**
     * @param terminated True if token is explicitly terminated
     */
    public final void setTerminated( boolean terminated )
    {
        this.terminated = terminated;
    }
}
