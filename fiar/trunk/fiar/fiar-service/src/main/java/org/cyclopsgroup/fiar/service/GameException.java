package org.cyclopsgroup.fiar.service;

/**
 * General game exceptions
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class GameException
    extends RuntimeException
{
    public GameException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public GameException( String message )
    {
        super( message );
    }
}
