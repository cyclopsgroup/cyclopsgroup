package com.cyclopsgroup.waterview;

public class UrlRedirection
    extends RedirectionTarget
{
    private String url;

    public UrlRedirection( String url )
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
}
