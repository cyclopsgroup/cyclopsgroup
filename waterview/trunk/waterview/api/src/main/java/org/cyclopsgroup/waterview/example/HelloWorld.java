package org.cyclopsgroup.waterview.example;

import org.cyclopsgroup.waterview.Module;

/**
 * A hello world page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */

public class HelloWorld
{
    /**
     * @return Name to say hello to
     */
    @Module( path = "/helloworld", returnVariable = "myName" )
    public String render()
    {
        return "Jiaqi";
    }
}
