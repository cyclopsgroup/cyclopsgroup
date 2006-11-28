package com.cyclopsgroup.laputa.identity;

public class NoSuchUserException
    extends Exception
{
    private static final long serialVersionUID = 5427749891914657100L;

    public NoSuchUserException( String userName )
    {
        super( "User " + userName + " doesn't exist" );
    }
}
