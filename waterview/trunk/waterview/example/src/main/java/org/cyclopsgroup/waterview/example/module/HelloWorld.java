package org.cyclopsgroup.waterview.example.module;

import org.cyclopsgroup.waterview.Mapping;

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

    public void setName( String name )
    {
        this.name = name;
    }

    public void execute()
    {
        message = "hello world, " + name + "!";
    }

    public String getMessage()
    {
        return message;
    }
}
