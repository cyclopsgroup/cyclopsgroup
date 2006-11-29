package com.cyclopsgroup.laputa.identity;

public class NoSuchUserException
    extends Exception
{
    private String userName;

    public NoSuchUserException( String userName )
    {
        super( "User " + userName + " doesn't exist" );
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
