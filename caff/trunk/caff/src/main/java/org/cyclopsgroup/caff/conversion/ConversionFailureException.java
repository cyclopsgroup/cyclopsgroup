package org.cyclopsgroup.caff.conversion;

/**
 * Exception that stands for conversion failure
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class ConversionFailureException
    extends RuntimeException
{
    private static final long serialVersionUID = 6702759460929375871L;

    /**
     * @param message Descriptive message
     * @param cause Root cause
     */
    public ConversionFailureException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
