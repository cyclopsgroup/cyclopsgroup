package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.Page;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Index home page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Module( path = "/index.vm" )
@Page( title = "Waterivew", description = "Welcome to waterview" )
public class Index
{
    /**
     * @param context Current web context
     */
    public void render( WebContext context )
    {
        context.setVariable( "indexVariable", "This is it" );
    }
}
