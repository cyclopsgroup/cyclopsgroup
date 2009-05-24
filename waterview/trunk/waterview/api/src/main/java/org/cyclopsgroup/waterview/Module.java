package org.cyclopsgroup.waterview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identify a class as web module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Module
{
    /**
     * @return Absolute path of module
     */
    String path();
    
    /**
     * @return Name of returned variable
     */
    String returnVariable() default "";
    
    /**
     * @return Customized template path. When template is not specified, the action name is used as template path
     */
    String template() default "";
}
