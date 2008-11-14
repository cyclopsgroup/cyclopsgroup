package org.cyclopsgroup.waterview.annotation;

/**
 * Flag for a field of form
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface FormField
{
    /**
     * @return Name of field
     */
    String name() default "";

    /**
     * @return Display name in error message
     */
    String displayName() default "";

    /**
     * @return True if field is required
     */
    boolean required() default false;

    /**
     * @return Discription of field
     */
    String description() default "";
}
