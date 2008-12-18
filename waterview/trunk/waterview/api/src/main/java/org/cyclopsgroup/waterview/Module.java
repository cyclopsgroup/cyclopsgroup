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
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface Module
{
    /**
     * @return Absolute path of module
     */
    String path();
    
    /**
     * @return Name of method that renders {@link WebContext}
     */
    String method() default "render";
}
