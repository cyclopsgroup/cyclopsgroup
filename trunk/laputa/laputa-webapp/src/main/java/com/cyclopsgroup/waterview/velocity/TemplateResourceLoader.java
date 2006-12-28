package com.cyclopsgroup.waterview.velocity;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TemplateResourceLoader
    extends ClasspathResourceLoader
{

    @Override
    public synchronized InputStream getResourceStream( String name )
        throws ResourceNotFoundException
    {
        // TODO Auto-generated method stub
        return super.getResourceStream( name );
    }

}
