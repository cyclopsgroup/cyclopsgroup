package org.cyclopsgroup.streammarker;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.Validate;

public class Application
{
    private final String applicationIdentifier;

    private String applicationName = "UndefinedApplication";

    private String locationName;

    private Collection<String> tags = Collections.emptyList();

    public Application()
        throws IOException
    {
        applicationIdentifier = ManagementFactory.getRuntimeMXBean().getName();
        locationName = InetAddress.getLocalHost().getHostName();
    }

    public final String getApplicationIdentifier()
    {
        return applicationIdentifier;
    }

    public final String getApplicationName()
    {
        return applicationName;
    }

    public final String getLocationName()
    {
        return locationName;
    }

    public final Collection<String> getTags()
    {
        return tags;
    }

    public final void setApplicationName( String application )
    {
        Validate.notNull( application, "Application can't be NULL" );
        Validate.isTrue( Mark.PATTERN_WORD.matcher( application ).find(), "Invalid application name " + application );
        this.applicationName = application;
    }

    public final void setLocationName( String locationName )
    {
        Validate.notNull( locationName, "Location name can't be NULL" );
        this.locationName = locationName;
    }

    public final void setTags( Collection<String> tags )
    {
        Validate.notNull( tags, "Tags can't be NULL" );
        Validate.noNullElements( tags, "Tag value can't be NULL" );
        for ( String tag : tags )
        {
            Validate.isTrue( Mark.PATTERN_WORD.matcher( tag ).find(), "Invalid tag value " + tag );
        }
        this.tags = tags;
    }
}
