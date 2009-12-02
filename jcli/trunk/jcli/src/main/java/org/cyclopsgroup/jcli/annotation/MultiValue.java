package org.cyclopsgroup.jcli.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * Mark an option that takes multple values
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Documented
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface MultiValue
{
    /**
     * @return Type of value in list
     */
    Class<?> valueType() default String.class;

    /**
     * @return Type of list that contains values
     */
    Class<?> listType() default ArrayList.class;

    /**
     * @return Max number of values in list. By default it's -1 that means unlimited
     */
    int maxValues() default -1;
}
