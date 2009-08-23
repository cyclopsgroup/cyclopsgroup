package org.cyclopsgroup.caff.format;

class InvalidTypeException
    extends RuntimeException
{
    private static final long serialVersionUID = 1013242349732621546L;

    InvalidTypeException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
