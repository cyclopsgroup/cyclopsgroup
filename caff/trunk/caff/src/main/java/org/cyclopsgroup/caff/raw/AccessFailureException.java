package org.cyclopsgroup.caff.raw;

/**
 * Runtime exception for read/write failures
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 *
 */
public class AccessFailureException
    extends RuntimeException
{
    private static final long serialVersionUID = -6606711393792663077L;

    /**
     * @param message Error message
     * @param cause Root cause
     */
    AccessFailureException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
