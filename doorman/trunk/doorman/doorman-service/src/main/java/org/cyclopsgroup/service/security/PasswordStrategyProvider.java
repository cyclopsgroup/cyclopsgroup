package org.cyclopsgroup.service.security;

import org.apache.commons.lang.StringUtils;

abstract class PasswordStrategyProvider
{
    abstract String encode( String password, String userId );

    boolean match( String password, String userId, String expected )
    {
        return StringUtils.equals( encode( password, userId ), expected );
    }
}
