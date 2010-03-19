package org.cyclopsgroup.jcli;

import org.cyclopsgroup.jcli.annotation.Option;

public class BeanWithoutCli
{
    String optionA;

    @Option( name = "a" )
    public void setOptionA( String value )
    {
        this.optionA = value;
    }
}
