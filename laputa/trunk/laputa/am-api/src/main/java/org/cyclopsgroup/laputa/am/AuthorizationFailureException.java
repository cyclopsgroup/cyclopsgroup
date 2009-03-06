package org.cyclopsgroup.laputa.am;

/**
 * Authorization failure exception
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class AuthorizationFailureException
    extends Exception
{
    private static final long serialVersionUID = 7630841148406850193L;

    private final AuthorizationFailure failureType;

    /**
     * @param failureType Type of failure which can't be NULL
     * @param message Verbal description of failure
     * @param cause Root cause
     */
    public AuthorizationFailureException( AuthorizationFailure failureType, String message, Throwable cause )
    {
        super( failureType + ":" + message, cause );
        this.failureType = failureType;
    }

    /**
     * @return Value of field failureType
     */
    public final AuthorizationFailure getFailureType()
    {
        return failureType;
    }
}
