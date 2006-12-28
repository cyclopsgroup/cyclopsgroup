package com.cyclopsgroup.waterview.velocity;

import java.io.IOException;

import javax.servlet.ServletConfig;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;

public class ViewServlet
    extends VelocityViewServlet
{
    @Override
    protected ExtendedProperties loadConfiguration( ServletConfig arg0 )
        throws IOException
    {
        ExtendedProperties p = new ExtendedProperties();
        p.setProperty( "resource.loader", "classpath" );
        p.setProperty( "classpath.resource.loader.class", TemplateResourceLoader.class.getName() );
        return p;
    }
}
