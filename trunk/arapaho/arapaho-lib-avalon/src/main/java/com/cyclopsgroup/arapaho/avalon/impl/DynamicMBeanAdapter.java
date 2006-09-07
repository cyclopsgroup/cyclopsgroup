package com.cyclopsgroup.arapaho.avalon.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.arapaho.avalon.MBeanAttribute;
import com.cyclopsgroup.arapaho.avalon.MBeanClass;
import com.cyclopsgroup.arapaho.avalon.MBeanOperation;

class DynamicMBeanAdapter
    implements DynamicMBean
{
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    private static final MBeanAttributeInfo[] EMPTY_MBEAN_ATTRIBUTE_INFO_ARRAY = new MBeanAttributeInfo[0];

    private static final MBeanOperationInfo[] EMPTY_MBEAN_OPERATION_INFO_ARRAY = new MBeanOperationInfo[0];

    private static final MBeanNotificationInfo[] EMPTY_MBEAN_NOTIFICATION_INFO_ARRAY = new MBeanNotificationInfo[0];

    private static final MBeanConstructorInfo[] EMPTY_MBEAN_CONSTRUCTOR_INFO_ARRAY = new MBeanConstructorInfo[0];

    private Log log;

    private Object component;

    private MBeanInfo mbeanInfo;

    DynamicMBeanAdapter( Object component, String componentKey )
        throws IntrospectionException
    {
        if ( component == null )
        {
            throw new IllegalArgumentException( "Component is null" );
        }
        this.component = component;
        this.mbeanInfo = createMBeanInfo( component, componentKey );
        log = LogFactory.getLog( component.getClass() );
    }

    private static MBeanInfo createMBeanInfo( Object component, String componentKey )
        throws IntrospectionException
    {
        final MBeanClass mbeanClass = component.getClass().getAnnotation( MBeanClass.class );
        if ( mbeanClass == null )
        {
            throw new IllegalArgumentException( "Component " + component
                + " must be annotated with MBeanClass annotation" );
        }
        final String classDescription = mbeanClass.value();

        final List<MBeanAttributeInfo> attributes = new ArrayList<MBeanAttributeInfo>();
        for ( final PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors( component.getClass() ) )
        {
            final Method readMethod = descriptor.getReadMethod();
            final Method writeMethod = descriptor.getWriteMethod();

            boolean attributeExposed = false;
            String description = descriptor.getDisplayName();
            if ( readMethod != null && readMethod.getAnnotation( MBeanAttribute.class ) != null )
            {
                attributeExposed = true;
            }

            if ( writeMethod != null && writeMethod.getAnnotation( MBeanAttribute.class ) != null )
            {
                attributeExposed = true;
            }

            if ( attributeExposed )
            {
                attributes.add( new MBeanAttributeInfo( descriptor.getName(), description, readMethod, writeMethod ) );
            }
        }
        final MBeanAttributeInfo[] attributeInfos = attributes.toArray( EMPTY_MBEAN_ATTRIBUTE_INFO_ARRAY );

        final List<MBeanOperationInfo> operations = new ArrayList<MBeanOperationInfo>();
        String operationDescription;
        for ( final Method method : component.getClass().getMethods() )
        {
            if ( ( method.getModifiers() & Modifier.PUBLIC ) != 0
                && method.getAnnotation( MBeanOperation.class ) != null )
            {
                operationDescription = method.getAnnotation( MBeanOperation.class ).value();
                operations.add( new MBeanOperationInfo( operationDescription, method ) );
            }
        }
        MBeanOperationInfo[] operationInfos = operations.toArray( EMPTY_MBEAN_OPERATION_INFO_ARRAY );

        return new MBeanInfo( component.getClass().getCanonicalName(), classDescription, attributeInfos,
                              EMPTY_MBEAN_CONSTRUCTOR_INFO_ARRAY, operationInfos, EMPTY_MBEAN_NOTIFICATION_INFO_ARRAY );
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
        Attribute attribute;
        for ( String attributeName : attributes )
        {
            try
            {
                attribute = new Attribute( attributeName, getAttribute( attributeName ) );
                list.add( attribute );
            }
            catch ( Exception e )
            {
                log.warn( "Getting attribute " + attributeName + " error", e );
            }
        }
        return list;
    }

    /**
     * @see javax.management.DynamicMBean#getMBeanInfo()
     */
    public MBeanInfo getMBeanInfo()
    {
        return mbeanInfo;
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
        for ( final Iterator<Attribute> i = attributes.iterator(); i.hasNext(); )
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
