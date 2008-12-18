package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.WebContext;

/**
 * A hello world page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Module( path = "/helloworld" )
public class HelloWorld
{
    /**
     * @param context Web context
     */
    public void render( WebContext context )
    {
        context.setVariable( "myName", "Jiaqi" );
    }
}
