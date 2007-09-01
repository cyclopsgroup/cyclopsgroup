package com.cyclopsgroup.nimbus;

public class NestedClientException
    extends ClientException
{
    private static final long serialVersionUID = 1L;

    public NestedClientException( String serviceClientId, Throwable e )
    {
        super( serviceClientId, "Misconfigured", e );
    }
}
