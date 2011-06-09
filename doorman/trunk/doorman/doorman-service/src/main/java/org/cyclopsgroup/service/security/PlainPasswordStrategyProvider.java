package org.cyclopsgroup.service.security;

class PlainPasswordStrategyProvider
    extends PasswordStrategyProvider
{
    /**
     * @inheritDoc
     */
    @Override
    String encode( String password, String userId )
    {
        return password;
    }
}
