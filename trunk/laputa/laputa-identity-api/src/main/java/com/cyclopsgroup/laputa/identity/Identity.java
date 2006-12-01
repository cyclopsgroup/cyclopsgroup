package com.cyclopsgroup.laputa.identity;

import java.util.Locale;
import java.util.TimeZone;

public interface Identity
{
    Locale getLocale();

    TimeZone getTimeZone();

    String getUserName();
}
