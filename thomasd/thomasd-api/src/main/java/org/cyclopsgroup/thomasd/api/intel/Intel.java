package org.cyclopsgroup.thomasd.api.intel;

public class Intel
{
    private IntelHeader header;

    private String body;

    public final String getBody()
    {
        return body;
    }

    public final IntelHeader getHeader()
    {
        return header;
    }

    public final void setBody( String body )
    {
        this.body = body;
    }

    public final void setHeader( IntelHeader header )
    {
        this.header = header;
    }
}
