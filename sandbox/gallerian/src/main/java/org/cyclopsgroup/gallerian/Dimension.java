package org.cyclopsgroup.gallerian;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XY dimension
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@XmlRootElement(name="Dimensino")
public class Dimension
{
    @XmlElement
    private int height;
    
    @XmlElement
    private int width;
    
    /**
     * Default constructor required by JAXB 
     */
    public Dimension()
    {
    }
    /**
     * @param width Width of dimension
     * @param height Height of dimension
     */
    public Dimension(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    /**
     * @return Value of field height
     */
    public final int getHeight()
    {
        return height;
    }
    /**
     * @return Value of field width
     */
    public final int getWidth()
    {
        return width;
    }
    /**
     * @param height Value of field height to set
     */
    public final void setHeight( int height )
    {
        this.height = height;
    }
    /**
     * @param width Value of field width to set
     */
    public final void setWidth( int width )
    {
        this.width = width;
    }
}
