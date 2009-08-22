package org.cyclopsgroup.caff.conversion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that marks another annotation as a conversion flag
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConversionSupported
{
    /**
     * @return Type of converter factory that manages converter
     */
    @SuppressWarnings( "unchecked" )
    Class<? extends ConverterFactory> factoryType();
}
