package org.cyclopsgroup.streammarker.plain;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.Validate;

public class Application
{
    private String applicationName = "UndefinedApplication";

    private final String locationIdentifier;

    private String locationName;

    private Collection<String> tags = Collections.emptyList();

    public Application()
        throws IOException
    {
        locationIdentifier = ManagementFactory.getRuntimeMXBean().getName();
        locationName = InetAddress.getLocalHost().getHostName();
    }

    public final String getApplicationName()
    {
        return applicationName;
    }

    public final String getLocationIdentifier()
    {
        return locationIdentifier;
    }

    public final String getLocationName()
    {
        return locationName;
    }

    public final Collection<String> getTags()
    {
        return tags;
    }

    public final void setApplicatiionName( String applicatiion )
    {
        Validate.notNull( locationName, "Application can't be NULL" );
        this.applicationName = applicatiion;
    }

    public final void setLocationName( String locationName )
    {
        Validate.notNull( locationName, "Location name can't be NULL" );
        this.locationName = locationName;
    }

    public final void setTags( Collection<String> tags )
    {
        Validate.notNull( tags, "Tags can't be NULL" );
        this.tags = tags;
    }
}
