package org.cyclopsgroup.caff.format;

/**
 * Formatting failure
 * 
 * @author jiaqi
 */
public class FormatException extends RuntimeException
{
    private static final long serialVersionUID = -5798512658644125136L;

    /**
     * @param message Descriptive message about this error
     */
    FormatException(String message)
    {
        super(message);
    }
}
