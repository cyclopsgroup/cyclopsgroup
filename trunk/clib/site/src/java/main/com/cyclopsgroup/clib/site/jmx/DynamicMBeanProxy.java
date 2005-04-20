package com.cyclopsgroup.clib.site.jmx;

import java.io.Serializable;
import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.ReflectionException;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Proxy dynamic mbean
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DynamicMBeanProxy implements DynamicMBean, Serializable
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3618141156026956087L;

    private Object component;

    private transient Log logger = LogFactory.getLog(getClass());

    private MBeanInfo mbeanInfo;

    private String modelerType = "proxy";

    /**
     * Constructor of class MBeanProxy
     *
     * @param component
     * @param mbeanInfo
     */
    public DynamicMBeanProxy(Object component, MBeanInfo mbeanInfo)
    {
        this.component = component;
        this.mbeanInfo = mbeanInfo;
    }

    /**
     * Override method getAttribute() in super class
     *
     * @see javax.management.DynamicMBean#getAttribute(java.lang.String)
     */
    public Object getAttribute(String attributeName)
            throws AttributeNotFoundException, MBeanException,
            ReflectionException
    {
        if (StringUtils.equals(attributeName, "modelerType"))
        {
            return modelerType;
        }
        try
        {
            return PropertyUtils.getProperty(component, attributeName);
        }
        catch (Exception e)
        {
            logger.warn("Get property error", e);
            return null;
        }
    }

    /**
     * Override method getAttributes() in super class
     *
     * @see javax.management.DynamicMBean#getAttributes(java.lang.String[])
     */
    public AttributeList getAttributes(String[] nameList)
    {
        AttributeList al = new AttributeList();
        for (int i = 0; i < nameList.length; i++)
        {
            String name = nameList[i];
            try
            {
                al.add(new Attribute(name, getAttribute(name)));
            }
            catch (Exception e)
            {
                logger.warn("Get attribute error", e);
            }
        }
        return al;
    }

    /**
     * Override method getMBeanInfo() in super class
     *
     * @see javax.management.DynamicMBean#getMBeanInfo()
     */
    public MBeanInfo getMBeanInfo()
    {
        return mbeanInfo;
    }

    /**
     * Override method invoke() in super class
     *
     * @see javax.management.DynamicMBean#invoke(java.lang.String, java.lang.Object[], java.lang.String[])
     */
    public Object invoke(String methodName, Object[] arguments,
            String[] signatures) throws MBeanException, ReflectionException
    {
        try
        {
            return MethodUtils.invokeMethod(component, methodName, arguments);
        }
        catch (Exception e)
        {
            throw new ReflectionException(e);
        }
    }

    /**
     * Override method setAttribute() in super class
     *
     * @see javax.management.DynamicMBean#setAttribute(javax.management.Attribute)
     */
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException
    {
        if (StringUtils.equals(attribute.getName(), "modelerType"))
        {
            modelerType = (String) attribute.getValue();
        }
        try
        {
            PropertyUtils.setProperty(component, attribute.getName(), attribute
                    .getValue());
        }
        catch (Exception e)
        {
            logger.debug("Set attribute error " + attribute, e);
        }
    }

    /**
     * Override method setAttributes() in super class
     *
     * @see javax.management.DynamicMBean#setAttributes(javax.management.AttributeList)
     */
    public AttributeList setAttributes(AttributeList list)
    {
        AttributeList al = new AttributeList();
        for (Iterator iter = list.iterator(); iter.hasNext();)
        {
            Attribute attribute = (Attribute) iter.next();
            try
            {
                setAttribute(attribute);
                al.add(attribute);
            }
            catch (Exception e)
            {
                logger.warn("Set attribute error", e);
            }
        }
        return al;
    }
}