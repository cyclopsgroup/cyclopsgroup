package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ViewOptions")
public class ListingOptions
{
    @XmlElement
    private int maxContents;
    
    @XmlElement
    private String sessionId;
}
