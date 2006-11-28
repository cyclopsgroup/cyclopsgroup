package com.cyclopsgroup.laputa.identity.memory;

import com.cyclopsgroup.laputa.identity.spi.SimpleIdentity;

class MemoryIdentity
    extends SimpleIdentity
{
    private String password;

    MemoryIdentity( String userName, String password )
    {
        super( userName );
        this.password = password;
    }

    String getPassword()
    {
        return password;
    }
}
