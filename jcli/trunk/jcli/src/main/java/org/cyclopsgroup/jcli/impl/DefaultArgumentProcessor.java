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
import org.cyclopsgroup.jcli.ArgumentProcessor;
import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.MultiValue;
import org.cyclopsgroup.jcli.annotation.Option;
import org.cyclopsgroup.jcli.spi.CommandLine;
import org.cyclopsgroup.jcli.spi.CommandLineParser;

class DefaultArgumentProcessor<T>
    extends ArgumentProcessor<T>
{
    private static final String ARGUMENT_REFERNCE_NAME = "----arguments----";

    private static <T> DefaultParsingContext<T> createParsingContext( Class<T> beanType )
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
                references.put( ARGUMENT_REFERNCE_NAME, createReference( beanType, descriptor, ARGUMENT_REFERNCE_NAME ) );
                continue;
            }
        }
        return new DefaultParsingContext<T>( beanType, references, options );
    }

    @SuppressWarnings( "unchecked" )
    private static <T> Reference<T> createReference( Class<T> beanType, PropertyDescriptor descriptor, String longName )
    {
        Method writer = descriptor.getWriteMethod();

        Class<?> valueType = descriptor.getPropertyType();

        Argument argument = getAnnotation( descriptor, Argument.class );
        if ( argument != null )
        {
            valueType = argument.type();
        }

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
        if ( argument != null && List.class.isAssignableFrom( descriptor.getPropertyType() ) )
        {
            return new MultiValueReference<T>( beanType, converter, reference, longName, ArrayList.class );
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

    private final DefaultParsingContext<T> context;

    private final CommandLineParser parser;

    DefaultArgumentProcessor( Class<T> beanType, CommandLineParser parser )
    {
        this.parser = parser;
        context = createParsingContext( beanType );
    }

    DefaultParsingContext<T> getContext()
    {
        return context;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void process( String[] arguments, T bean )
    {
        CommandLine cli = parser.parse( arguments, context );
        Map<String, List<String>> multiValues = new HashMap<String, List<String>>();
        for ( CommandLine.OptionValue ov : cli.getOptionValues() )
        {
            Reference<T> ref = context.lookupReference( ov.name, !ov.shortName );
            if ( ref == null )
            {
                throw new AssertionError( "Option " + ov.name + " doesn't exist" );
            }
            if ( ref instanceof SingleValueReference<?> )
            {
                ( (SingleValueReference<T>) ref ).setValue( bean, ov.value );
                continue;
            }
            List<String> values = multiValues.get( ov.name );
            if ( values == null )
            {
                values = new ArrayList<String>();
                if ( ov.shortName )
                {
                    multiValues.put( ov.name, values );
                }
                else
                {
                    multiValues.put( context.optionWithLongName( ov.name ).getName(), values );
                }

            }
            values.add( ov.value );
        }
        for ( Map.Entry<String, List<String>> entry : multiValues.entrySet() )
        {
            MultiValueReference<T> ref = (MultiValueReference<T>) context.lookupReference( entry.getKey(), false );
            ref.setValues( bean, entry.getValue() );
        }
        Reference<T> ref = context.lookupReference( ARGUMENT_REFERNCE_NAME, false );
        if ( ref == null )
        {
            return;
        }
        if ( ref instanceof MultiValueReference<?> )
        {
            ( (MultiValueReference<T>) ref ).setValues( bean, cli.getArguments() );
        }
        else
        {
            String value = cli.getArguments().isEmpty() ? null : cli.getArguments().get( 0 );
            ( (SingleValueReference<T>) ref ).setValue( bean, value );
        }
    }
}