package org.cyclopsgroup.waterview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Flag an object as a page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Page
{
    /**
     * @return MIME content type of this page
     */
    String contentType() default Constants.DEFAULT_CONTENT_TYPE;

    /**
     * @return Detail description of this page
     */
    String description() default "";

    /**
     * @return Keywords of this page
     */
    String[] keywords() default {};

    /**
     * @return Render page with specified layout
     */
    String layout() default "";
    
    /**
     * @return Set it to true if module take care of entire rendering process
     */
    boolean raw() default false;

    /**
     * @return Title of page
     */
    String title() default "";
}
