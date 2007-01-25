package com.cyclopsgroup.waterview.impl.valves;

public class Http404Exception
    extends Exception
{
    public Http404Exception( String requestPath )
    {
        super( "404 Error. Resource " + requestPath + " is not found" );
    }
}
