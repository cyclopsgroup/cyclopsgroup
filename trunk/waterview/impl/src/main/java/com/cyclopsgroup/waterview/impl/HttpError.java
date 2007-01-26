package com.cyclopsgroup.waterview.impl;

public class HttpError
    extends Error
{
    private String errorMessage;

    private int statusCode;

    public HttpError( int statusCode )
    {
        this( statusCode, null );
    }

    public HttpError( int statusCode, String errorMessage )
    {
        super( "HTTP Error " + statusCode + ": " + errorMessage );
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public int getStatusCode()
    {
        return statusCode;
    }
}
