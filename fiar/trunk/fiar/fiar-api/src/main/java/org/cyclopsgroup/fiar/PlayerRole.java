package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum PlayerRole
{
    @XmlEnumValue( "X" )
    OFFENSE,
    @XmlEnumValue( "O" )
    DEFENSE;
}
