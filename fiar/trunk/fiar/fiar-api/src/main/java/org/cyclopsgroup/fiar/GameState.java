package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum GameState
{
    @XmlEnumValue( "P" )
    PENDING, @XmlEnumValue( "O" )
    ONGOING, @XmlEnumValue( "E" )
    EXPIRED, @XmlEnumValue( "F" )
    FINISHED;
}
