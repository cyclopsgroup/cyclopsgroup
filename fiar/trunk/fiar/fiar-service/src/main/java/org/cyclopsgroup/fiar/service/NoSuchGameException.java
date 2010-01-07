package org.cyclopsgroup.fiar.service;

/**
 * Referencing a game that doesn't exist
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class NoSuchGameException
    extends GameException
{
    public NoSuchGameException( String message )
    {
        super( message );
    }
}
