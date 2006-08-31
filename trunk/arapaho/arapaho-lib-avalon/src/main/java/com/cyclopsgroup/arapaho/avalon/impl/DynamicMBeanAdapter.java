package com.cyclopsgroup.arapaho.avalon.impl;

import java.beans.PropertyDescriptor;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class DynamicMBeanAdapter
    implements DynamicMBean
{
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    private Log log;

    private Object component;

    DynamicMBeanAdapter( Object component )
    {
        if ( component == null )
        {
            throw new IllegalArgumentException( "Component is null" );
        }
        this.component = component;
        log = LogFactory.getLog( component.getClass() );
    }

    public Object getAttribute( String attribute )
        throws AttributeNotFoundException, MBeanException, ReflectionException
    {
        try
        {
            PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor( component, attribute );
            if ( descriptor == null )
            {
                throw new AttributeNotFoundException();
            }
            return descriptor.getReadMethod().invoke( component, EMPTY_OBJECT_ARRAY );
        }
        catch ( AttributeNotFoundException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ReflectionException( e );
        }
    }

    /**
     * @see javax.management.DynamicMBean#getAttributes(java.lang.String[])
     */
    public AttributeList getAttributes( String[] attributes )
    {
        final AttributeList list = new AttributeList();
        for ( String attributeName : attributes )
        {
            try
            {
                final Attribute attribute = new Attribute( attributeName, getAttribute( attributeName ) );
                list.add( attribute );
            }
            catch ( Exception e )
            {
                log.warn( "Getting attribute " + attributeName + " error", e );
            }
        }
        return list;
    }

    public MBeanInfo getMBeanInfo()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see javax.management.DynamicMBean#invoke(java.lang.String, java.lang.Object[], java.lang.String[])
     */
    public Object invoke( String actionName, Object[] params, String[] signature )
        throws MBeanException, ReflectionException
    {
        try
        {
            return MethodUtils.invokeMethod( component, actionName, params );
        }
        catch ( Exception e )
        {
            throw new ReflectionException( e );
        }
    }

    public void setAttribute( Attribute attribute )
        throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException
    {
        try
        {
            PropertyUtils.setProperty( component, attribute.getName(), attribute.getValue() );
        }
        catch ( Exception e )
        {
            throw new ReflectionException( e );
        }
    }

    @SuppressWarnings("unchecked")
    public AttributeList setAttributes( AttributeList attributes )
    {
        final AttributeList responseList = new AttributeList();
        for ( Iterator<Attribute> i = attributes.iterator(); i.hasNext(); )
        {
            final Attribute attr = i.next();
            try
            {
                setAttribute( attr );
                responseList.add( attr );
            }
            catch ( Exception excep )
            {
                responseList.remove( attr );
            }
        }
        return responseList;
    }
}
