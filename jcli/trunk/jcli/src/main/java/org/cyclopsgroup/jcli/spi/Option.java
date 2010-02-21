package org.cyclopsgroup.jcli.spi;

public interface Option
{
    String getName();

    String getLongName();

    boolean isFlag();
}
