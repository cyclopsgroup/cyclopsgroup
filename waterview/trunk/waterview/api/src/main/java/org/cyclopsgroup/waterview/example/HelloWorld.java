package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.WebModule;

/**
 * A hello world page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class HelloWorld
    implements WebModule
{
    /**
     * @inheritDoc
     */
    public void beforeRender( WebContext context )
    {
        context.setVariable( "myName", "Jiaqi" );
    }
}
