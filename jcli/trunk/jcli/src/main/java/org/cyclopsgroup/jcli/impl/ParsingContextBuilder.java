package org.cyclopsgroup.jcli.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.caff.conversion.AnnotatedConverter;
import org.cyclopsgroup.caff.conversion.Converter;
import org.cyclopsgroup.caff.conversion.NullFriendlyConverter;
import org.cyclopsgroup.caff.conversion.SimpleConverter;
import org.cyclopsgroup.caff.ref.ValueReference;
import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.MultiValue;
import org.cyclopsgroup.jcli.annotation.Option;

class ParsingContextBuilder<T>
{
    private final Class<T> beanType;

    ParsingContextBuilder( Class<T> beanType )
    {
        this.beanType = beanType;
    }

    DefaultParsingContext<T> build()
    {
        List<AnnotationOption> options = new ArrayList<AnnotationOption>();
        Map<String, Reference<T>> references = new HashMap<String, Reference<T>>();
        Cli cliAnnotation = beanType.getAnnotation( Cli.class );
        Validate.notNull( cliAnnotation, "Type " + beanType + " has to be annotated with @Cli" );
        BeanInfo beanInfo;
        try
        {
            beanInfo = Introspector.getBeanInfo( beanType );
        }
        catch ( IntrospectionException e )
        {
            throw new RuntimeException( "Bean " + beanType + " is not correctly defined", e );
        }
        for ( PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors() )
        {
            Method writer = descriptor.getWriteMethod();
            if ( writer == null )
            {
                continue;
            }
            Option option = getAnnotation( descriptor, Option.class );
            if ( option != null )
            {
                boolean flag =
                    ( descriptor.getPropertyType() == Boolean.TYPE || descriptor.getPropertyType() == Boolean.class );
                options.add( new AnnotationOption( option, flag ) );
                references.put( option.name(), createReference( beanType, descriptor, option.longName() ) );
                continue;
            }
            Argument argument = getAnnotation( descriptor, Argument.class );
            if ( argument != null )
            {
                references.put( DefaultArgumentProcessor.ARGUMENT_REFERNCE_NAME,
                                createReference( beanType, descriptor, DefaultArgumentProcessor.ARGUMENT_REFERNCE_NAME ) );
                continue;
            }
        }
        return new DefaultParsingContext<T>( beanType, references, options, new AnnotationCli( cliAnnotation ) );
    }

    @SuppressWarnings( "unchecked" )
    private static <T> Reference<T> createReference( Class<T> beanType, PropertyDescriptor descriptor, String longName )
    {
        Method writer = descriptor.getWriteMethod();

        Class<?> valueType = descriptor.getPropertyType();

        MultiValue multiValue = getAnnotation( descriptor, MultiValue.class );
        if ( multiValue != null )
        {
            valueType = multiValue.valueType();
        }

        Converter internalConverter =
            AnnotatedConverter.isConversionSupported( writer ) ? new AnnotatedConverter( valueType, writer )
                            : new SimpleConverter( valueType );
        Converter converter = new NullFriendlyConverter( internalConverter );
        ValueReference<T> reference = ValueReference.instanceOf( descriptor );
        if ( multiValue != null )
        {
            return new MultiValueReference<T>( beanType, converter, reference, longName, multiValue.listType() );
        }
        return new SingleValueReference<T>( beanType, converter, reference, longName );
    }

    /**
     * Get annotation from either writer or reader of a Java property
     *
     * @param <A> Type of annotation
     * @param descriptor Field descriptor from which annotation is searched
     * @param type Type of annotation
     * @return Annotation or null if it's not found
     */
    private static <A extends Annotation> A getAnnotation( PropertyDescriptor descriptor, Class<A> type )
    {
        A a = null;
        if ( descriptor.getWriteMethod() != null )
        {
            a = descriptor.getWriteMethod().getAnnotation( type );
        }
        if ( a == null && descriptor.getReadMethod() != null )
        {
            a = descriptor.getReadMethod().getAnnotation( type );
        }
        return a;
    }
}
