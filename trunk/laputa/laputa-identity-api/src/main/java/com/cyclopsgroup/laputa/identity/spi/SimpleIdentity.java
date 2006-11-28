package com.cyclopsgroup.laputa.identity.spi;

import java.io.Serializable;

import com.cyclopsgroup.laputa.identity.Identity;

public class SimpleIdentity
    implements Identity, Serializable
{
    private static final long serialVersionUID = 5106821401019346692L;

    private String userName;

    public SimpleIdentity( String userName )
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
