package org.cyclopsgroup.fiar.service.mem;

import org.cyclopsgroup.fiar.service.UserSessionService;

public class ConstantUserSessionService
    implements UserSessionService
{
    /**
     * @inheritDoc
     */
    @Override
    public String getUserOfSession( String sessionId )
    {
        return sessionId;
    }
}
