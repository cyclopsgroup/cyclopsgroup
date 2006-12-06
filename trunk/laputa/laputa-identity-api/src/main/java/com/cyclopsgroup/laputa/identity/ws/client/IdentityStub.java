package com.cyclopsgroup.laputa.identity.ws.client;

import java.util.Locale;
import java.util.TimeZone;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.spi.SimpleIdentity;

class IdentityStub
    extends SimpleIdentity
    implements Identity
{
    public IdentityStub( com.cyclopsgroup.laputa.identity.ws.client.IdentityServiceStub.Identity stub )
    {
        super( stub.getUsername() );
        setLocale( new Locale( stub.getLanguage(), stub.getCountry() ) );
        setTimeZone( TimeZone.getTimeZone( stub.getTimezone() ) );
    }
}
