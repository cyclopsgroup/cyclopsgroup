package com.cyclopsgroup.waterview.alternative;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import com.cyclopsgroup.waterview.spi.WaterviewSpi;

public abstract class AbstractWaterview
    implements WaterviewSpi
{
    private final Map<String, String> packageMap = new Hashtable<String, String>();

    private final Map<String, String> readOnlyPackageMap = Collections.unmodifiableMap( packageMap );

    private Map<String, Object> initialProperties;

    public Map<String, Object> getInitialProperties()
    {
        return initialProperties;
    }

    @SuppressWarnings("unchecked")
    public void setInitialProperties( Map<String, ? extends Object> props )
    {
        initialProperties = (Map<String, Object>) props;
    }

    public void registerPackage( String alias, String packageName )
    {
        packageMap.put( alias, packageName );
    }

    public Map<String, String> getPackageMap()
    {
        return readOnlyPackageMap;
    }
}
