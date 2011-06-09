package org.cyclopsgroup.service.security;

/**
 * Enum of supported approaches to store and match password
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum PasswordStrategy
{
    /**
     * Plain text password is preserved
     */
    PLAIN( new PlainPasswordStrategyProvider() ),
    /**
     * Password is encoded with MD5 32
     */
    MD5( new MD5PasswordStrategyProvider() );

    private final PasswordStrategyProvider provider;

    private PasswordStrategy( PasswordStrategyProvider provider )
    {
        this.provider = provider;
    }

    /**
     * Encode password to value to store
     *
     * @param password Plain text password
     * @param userId Id of user to store password for
     * @return Encoded password
     */
    public String encode( String password, String userId )
    {
        return provider.encode( password, userId );
    }

    /**
     * Verify if given password matches stored encoded password
     *
     * @param password Given password to match
     * @param userId Id of user to match
     * @param expected Expected encoded password
     * @return True if password matches
     */
    public boolean match( String password, String userId, String expected )
    {
        return provider.match( password, userId, expected );
    }
}
