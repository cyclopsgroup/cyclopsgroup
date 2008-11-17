package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.WebModuleSuite;

/**
 * Module suite for examples
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ExampleModuleSuite
    extends WebModuleSuite
{
    /**
     * @inheritDoc
     */
    @Override
    protected void init()
    {
        addModuleType( "helloworld.vm", HelloWorld.class );
    }
}
