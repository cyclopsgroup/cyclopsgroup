package org.cyclopsgroup.thomasd.api.intel;


public class IntelHeader
{
    private String intelName;

    private long version;

    private IntelType intelType;

    private boolean active;

    private long bodySize;

    public final long getBodySize()
    {
        return bodySize;
    }

    public final String getIntelName()
    {
        return intelName;
    }

    public final IntelType getIntelType()
    {
        return intelType;
    }

    public final long getVersion()
    {
        return version;
    }

    public final boolean isActive()
    {
        return active;
    }

    public final void setActive( boolean active )
    {
        this.active = active;
    }

    public final void setBodySize( long bodySize )
    {
        this.bodySize = bodySize;
    }

    public final void setIntelName( String intelName )
    {
        this.intelName = intelName;
    }

    public final void setIntelType( IntelType intelType )
    {
        this.intelType = intelType;
    }

    public final void setVersion( long version )
    {
        this.version = version;
    }
}
