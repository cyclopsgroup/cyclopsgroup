package org.cyclopsgroup.waterview.example.module;

import org.cyclopsgroup.waterview.Mapping;
import org.cyclopsgroup.waterview.Stage;
import org.cyclopsgroup.waterview.StageInjection;

/**
 * A hello world page
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Mapping( path = "/hello", template = "/helloworld.vm" )
public class HelloWorld
{
    private String message;

    private String name = "unknown";

    @StageInjection( stage = Stage.INITIALIZE )
    public void setName( String name )
    {
        this.name = name;
    }

    @StageInjection( stage = Stage.EXECUTE )
    public void execute()
    {
        message = "hello world, " + name + "!";
    }

    @StageInjection( stage = Stage.OUTPUT )
    public String getMessage()
    {
        return message;
    }
}
