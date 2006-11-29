package com.cyclopsgroup.laputa.identity;

import java.util.Locale;
import java.util.TimeZone;

public interface Identity
{
    //TODO Will add more stuff here

    Locale getLocale();

    TimeZone getTimeZone();

    String getUserName();
}
