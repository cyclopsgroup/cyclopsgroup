package com.cyclopsgroup.laputa.identity.spi;

import java.io.Serializable;

import com.cyclopsgroup.laputa.identity.Identity;

/**
 * A simple implementation of Identity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class SimpleIdentity
    implements Identity, Serializable
{
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
