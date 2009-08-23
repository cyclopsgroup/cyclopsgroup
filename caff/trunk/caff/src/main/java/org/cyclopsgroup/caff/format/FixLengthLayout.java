package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


public class FixLengthLayout<T>
    implements Layout<T>
{
    public FixLengthLayout( Class<T> beanType )
    {

    }

    @Override
    public void populate( T object, Reader reader )
        throws IOException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print( T object, Writer output )
        throws IOException
    {
        // TODO Auto-generated method stub
        
    }
}
