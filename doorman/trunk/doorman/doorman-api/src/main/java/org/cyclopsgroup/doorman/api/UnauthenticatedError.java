package org.cyclopsgroup.doorman.api;

/**
 * A special internal error which notify caller current session isn't authenticated yet
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class UnauthenticatedError
    extends Error
{
}
