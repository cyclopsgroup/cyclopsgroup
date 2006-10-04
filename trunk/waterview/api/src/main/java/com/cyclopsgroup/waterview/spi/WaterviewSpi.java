package com.cyclopsgroup.waterview.spi;

import java.util.Map;

import com.cyclopsgroup.waterview.Waterview;

public interface WaterviewSpi
    extends Waterview
{
    void setInitialProperties( Map<String, ? extends Object> props );

    Map<String, Object> getInitialProperties();

    void registerPackage( String alias, String packageName );
}
