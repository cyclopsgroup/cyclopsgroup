package com.cyclopsgroup.laputa.identity.spi;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

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
    private Locale locale = Locale.getDefault();

    private TimeZone timeZone = TimeZone.getDefault();

    private String userName;

    public SimpleIdentity( String userName )
    {
        this.userName = userName;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setLocale( Locale locale )
    {
        this.locale = locale;
    }

    public void setTimeZone( TimeZone timeZone )
    {
        this.timeZone = timeZone;
    }
}
