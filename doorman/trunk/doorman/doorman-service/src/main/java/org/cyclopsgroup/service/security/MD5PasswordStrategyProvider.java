package org.cyclopsgroup.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

class MD5PasswordStrategyProvider
    extends PasswordStrategyProvider
{
    private final MessageDigest prototypeDigest;

    MD5PasswordStrategyProvider()
    {
        try
        {
            prototypeDigest = MessageDigest.getInstance( "MD5" );
        }
        catch ( NoSuchAlgorithmException e )
        {
            throw new IllegalStateException( "MD5 digest can't be created: " + e.getMessage(), e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    String encode( String password, String userId )
    {
        MessageDigest digest;
        try
        {
            digest = (MessageDigest) prototypeDigest.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new IllegalStateException( "Cant create instance of digest", e );
        }
        digest.reset();
        digest.update( userId.getBytes() );
        digest.update( password.getBytes() );
        byte[] hash = digest.digest();
        return Base64.encodeBase64URLSafeString( hash );
    }

    /**
     * @inheritDoc
     */
    @Override
    boolean match( String password, String userId, String expected )
    {
        return StringUtils.equals( encode( password, userId ), expected );
    }

}
