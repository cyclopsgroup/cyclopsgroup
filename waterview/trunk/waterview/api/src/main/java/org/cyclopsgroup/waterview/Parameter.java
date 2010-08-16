package org.cyclopsgroup.waterview;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Value comes from one of parameter
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Target( ElementType.PARAMETER )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Parameter
{
    /**
     * @return Name of parameter
     */
    String name();

    /**
     * @return Type of parameter
     */
    ParameterType type() default ParameterType.QUERY;
}
