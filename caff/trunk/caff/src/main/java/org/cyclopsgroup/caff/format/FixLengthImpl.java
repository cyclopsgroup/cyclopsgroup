package org.cyclopsgroup.caff.format;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.cyclopsgroup.caff.conversion.AnnotatedConverter;
import org.cyclopsgroup.caff.conversion.Converter;
import org.cyclopsgroup.caff.conversion.NullFriendlyConverter;
import org.cyclopsgroup.caff.conversion.SimpleConverter;
import org.cyclopsgroup.caff.ref.ValueReference;

/**
 * Internal actual fix-length algorithm
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of bean to format/parse
 */
class FixLengthImpl<T>
{
    private static <T> AccessibleObject findAnnotatedAccess( PropertyDescriptor desc, Class<T> type )
    {
        Field field;
        try
        {
            field = type.getClass().getField( desc.getName() );
        }
        catch ( Throwable e )
        {
            field = null;
        }
        AccessibleObject access = null;
        for ( AccessibleObject a : Arrays.asList( (AccessibleObject) desc.getReadMethod(), desc.getWriteMethod(), field ) )
        {
            if ( a != null && a.isAnnotationPresent( FixLengthField.class ) )
            {
                access = a;
                break;
            }
        }
        return access;
    }

    @SuppressWarnings( "unchecked" )
    private static <T> Converter<Object> createConverter( AccessibleObject access, Class<T> valueType )
    {
        Converter<T> c;
        if ( AnnotatedConverter.isConversionSupported( access ) )
        {
            c = new AnnotatedConverter<T>( valueType, access );
        }
        else
        {
            c = new SimpleConverter<T>( valueType );
        }
        return (Converter<Object>) new NullFriendlyConverter<T>( c );
    }

    private class Slot
    {
        private final Converter<Object> converter;

        private final FixLengthField field;

        private final char fill;

        private final ValueReference<T> reference;

        private Slot( FixLengthField field, Converter<Object> converter, ValueReference<T> reference )
        {
            this.field = field;
            this.converter = converter;
            this.reference = reference;
            this.fill = field.fill() == 0 ? type.fill() : field.fill();
        }
    }

    private final Map<String, Slot> slots;

    /**
     * Message level meta data
     */
    final FixLengthType type;

    /**
     * @param beanType type of bean to format/parse
     */
    FixLengthImpl( Class<T> beanType )
    {
        FixLengthType type = beanType.getAnnotation( FixLengthType.class );
        if ( type == null )
        {
            throw new IllegalArgumentException( "Type " + beanType + " isn't annotated with " + FixLengthType.class );
        }
        this.type = type;

        BeanInfo beanInfo;
        try
        {
            beanInfo = Introspector.getBeanInfo( beanType );
        }
        catch ( IntrospectionException e )
        {
            throw new RuntimeException( "Can't get bean info of " + beanType );
        }

        Map<String, Slot> slots = new HashMap<String, Slot>();
        for ( PropertyDescriptor desc : beanInfo.getPropertyDescriptors() )
        {
            AccessibleObject access = findAnnotatedAccess( desc, beanType );
            if ( access == null )
            {
                continue;
            }
            ValueReference<T> reference;
            if ( access instanceof Field )
            {
                reference = ValueReference.instanceOf( (Field) access );
            }
            else
            {
                reference = ValueReference.instanceOf( desc );
            }
            Slot slot =
                new Slot( access.getAnnotation( FixLengthField.class ),
                          createConverter( access, desc.getPropertyType() ), reference );
            slots.put( desc.getName(), slot );
        }
        for ( Field f : beanType.getFields() )
        {
            if ( slots.containsKey( f.getName() ) )
            {
                continue;
            }
            FixLengthField field = f.getAnnotation( FixLengthField.class );
            if ( field == null )
            {
                continue;
            }
            ValueReference<T> reference = ValueReference.instanceOf( f );
            Slot slot = new Slot( field, createConverter( f, f.getType() ), reference );
            slots.put( f.getName(), slot );
        }
        this.slots = Collections.unmodifiableMap( slots );
    }

    /**
     * @param bean Empty bean to populate
     * @param input A line of input
     */
    void populate( T bean, CharSequence input )
    {
        for ( Slot slot : slots.values() )
        {
            if ( !slot.reference.isWritable() )
            {
                continue;
            }
            if ( slot.field.start() >= input.length() )
            {
                continue;
            }
            CharSequence content =
                input.subSequence( slot.field.start(), Math.min( slot.field.start() + slot.field.length(),
                                                                 input.length() ) );
            content = slot.field.align().trim( content, slot.fill );
            Object value = slot.converter.fromCharacters( content );
            slot.reference.writeValue( value, bean );
        }
    }

    /**
     * Print bean into given char array
     *
     * FIXME It's not completely working yet
     *
     * @param bean Bean to print
     * @return Char array of result
     */
    char[] print( T bean )
    {
        char[] output = new char[type.length()];
        Arrays.fill( output, type.fill() );
        for ( Slot slot : slots.values() )
        {
            if ( !slot.reference.isReadable() )
            {
                continue;
            }
            Object value = slot.reference.readValue( bean );
            CharSequence content = slot.converter.toCharacters( value );
            if ( content.length() > slot.field.length() )
            {
                content = slot.field.trim().trim( content, slot.field.length(), slot.field.align() );
            }
            slot.field.align().fill( content, output, slot.field.start(), slot.field.length(), slot.fill );

        }
        return output;
    }
}
