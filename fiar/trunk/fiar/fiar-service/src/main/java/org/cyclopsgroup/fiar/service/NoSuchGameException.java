package org.cyclopsgroup.fiar.service;


public class NoSuchGameException
    extends GameException
{
    public NoSuchGameException( String message )
    {
        super( message );
    }
}
