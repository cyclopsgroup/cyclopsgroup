package org.cyclopsgroup.thomasd.api.intel;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import javax.ws.rs.core.StreamingOutput;

public interface IntelService
{
    StreamingOutput decrypt( InputStream input, String name )
        throws IOException, GeneralSecurityException;

    StreamingOutput decrypt( InputStream input, String name, String version )
        throws IOException, GeneralSecurityException;

    StreamingOutput encrypt( InputStream input, String name )
        throws IOException, GeneralSecurityException;

    StreamingOutput encrypt( InputStream input, String name, String version )
        throws IOException, GeneralSecurityException;

    Intel read( String name )
        throws IOException;

    Intel read( String name, long version )
        throws IOException;
}
