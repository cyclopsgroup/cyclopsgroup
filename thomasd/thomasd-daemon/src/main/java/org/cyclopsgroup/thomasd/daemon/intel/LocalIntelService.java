package org.cyclopsgroup.thomasd.daemon.intel;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import javax.ws.rs.core.StreamingOutput;

import org.cyclopsgroup.thomasd.api.intel.Intel;
import org.cyclopsgroup.thomasd.api.intel.IntelService;

public class LocalIntelService
    implements IntelService
{
    public StreamingOutput decrypt( InputStream input, String name )
        throws IOException, GeneralSecurityException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public StreamingOutput decrypt( InputStream input, String name, String version )
        throws IOException, GeneralSecurityException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public StreamingOutput encrypt( InputStream input, String name )
        throws IOException, GeneralSecurityException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public StreamingOutput encrypt( InputStream input, String name, String version )
        throws IOException, GeneralSecurityException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Intel read( String name )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Intel read( String name, long version )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
