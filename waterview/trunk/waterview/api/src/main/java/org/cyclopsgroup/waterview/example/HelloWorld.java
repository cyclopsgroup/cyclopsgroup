package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.RenderableModule;
import org.cyclopsgroup.waterview.annotation.Page;

/**
 * A hello world page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Page( name = "helloworld" )
public class HelloWorld
    implements RenderableModule
{
    /**
     * @inheritDoc
     */
    public void beforeRender( WebContext context )
    {
        context.setVariable( "myName", "Jiaqi" );
    }
}
