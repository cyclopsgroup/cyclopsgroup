package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.ModuleContext;
import org.cyclopsgroup.waterview.ViewableModule;
import org.cyclopsgroup.waterview.annotation.WebPage;

/**
 * A hello world page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@WebPage( name = "helloworld" )
public class HelloWorld
    implements ViewableModule
{
    /**
     * @inheritDoc
     */
    public void beforeRender( ModuleContext context )
    {
        context.setVariable( "myName", "Jiaqi" );
    }
}
