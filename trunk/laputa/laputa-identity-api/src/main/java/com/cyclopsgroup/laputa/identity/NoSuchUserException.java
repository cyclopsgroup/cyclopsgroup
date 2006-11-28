package com.cyclopsgroup.laputa.identity;

public class NoSuchUserException
    extends Exception
{
    public NoSuchUserException( String userName )
    {
        super( "User " + userName + " doesn't exist" );
    }
}
