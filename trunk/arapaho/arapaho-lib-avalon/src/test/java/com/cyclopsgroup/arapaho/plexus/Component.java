package com.cyclopsgroup.arapaho.plexus;


public interface Component
{
    public static final String ROLE = Component.class.getName();

    public boolean isInitialized();

    public String getTestValue();
}
