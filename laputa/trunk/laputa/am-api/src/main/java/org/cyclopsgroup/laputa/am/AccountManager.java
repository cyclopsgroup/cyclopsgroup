package org.cyclopsgroup.laputa.am;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Facade interface of Laputa account manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@WebService
public interface AccountManager
{
    /**
     * @param sessionId Session ID
     * @param userName Login user name
     * @param password Login password
     * @return Authorized session object
     * @throws AuthorizationFailureException Failed because invalid input
     */
    AuthorizedSession authorize( @WebParam( name = "sessionId" ) String sessionId,
                                 @WebParam( name = "userName" ) String userName,
                                 @WebParam( name = "password" ) String password )
        throws AuthorizationFailureException;

    /**
     * Get existing session with given ID
     * 
     * @param sessionId Given session ID
     * @return Session object
     */
    Session getSession( @WebParam( name = "sessionId" ) String sessionId );

    /**
     * Ping method
     * 
     * @param msg Input message
     * @return Echo'ed message
     */
    String ping( @WebParam( name = "message" ) String msg );

    /**
     * @param ipAddress Source IP address
     * @param macAddress Optional source MAC address
     * @return Registered anonymous session
     */
    AnonymousSession registerSession( @WebParam( name = "ipAddress" ) String ipAddress,
                                      @WebParam( name = "macAddress" ) String macAddress );
}
