package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.nio.CharBuffer;

import org.cyclopsgroup.caff.CharIterator;

/**
 * A general class that knows how to parse CSV syntax
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class CSVParser
{
    private class ParsingContext
    {
        private final CharBuffer buffer = CharBuffer.allocate( BUFFER_SIZE );

        private int position = 0;

        private ParsingState state = ParsingState.START;

        private void notifyField()
            throws IOException
        {
            buffer.flip();
            handleField( position, buffer );
            buffer.clear();
            position++;
        }
    }

    private static enum ParsingState
    {
        ESCAPING, QUOTING, START, WORD;
    };

    private static final int BUFFER_SIZE = 100;

    /**
     * @param in Char iterator as input
     * @throws IOException Allows {@link IOException}
     */
    public final void parse( CharIterator in )
        throws IOException
    {
        ParsingContext context = new ParsingContext();
        while ( in.hasNext() )
        {
            char ch = in.next();
            switch ( context.state )
            {
                case START:
                    switch ( ch )
                    {
                        case ' ':
                            break;
                        case ',':
                            context.notifyField();
                            break;
                        case '\"':
                            context.state = ParsingState.QUOTING;
                            break;
                        default:
                            context.state = ParsingState.WORD;
                            context.buffer.append( ch );
                    }
                    break;
                case WORD:
                    switch ( ch )
                    {
                        case ',':
                            context.notifyField();
                            context.state = ParsingState.START;
                            break;
                        default:
                            context.buffer.append( ch );
                    }
                    break;
                case QUOTING:
                    switch ( ch )
                    {
                        case '"':
                            context.state = ParsingState.ESCAPING;
                            break;
                        default:
                            context.buffer.append( ch );
                    }
                    break;
                case ESCAPING:
                    switch ( ch )
                    {
                        case ',':
                            context.notifyField();
                            context.state = ParsingState.START;
                            break;
                        default:
                            context.buffer.append( ch );
                            context.state = ParsingState.QUOTING;
                    }
                    break;
                default:
                    throw new AssertionError( "Nonsense state " + context.state );
            }
        }
        if ( context.buffer.position() > 0 )
        {
            context.notifyField();
        }
    }

    /**
     * @param position Zero based CSV field position
     * @param content Content of CSV field
     * @throws IOException Allows {@link IOException}
     */
    protected abstract void handleField( int position, CharSequence content )
        throws IOException;
}
