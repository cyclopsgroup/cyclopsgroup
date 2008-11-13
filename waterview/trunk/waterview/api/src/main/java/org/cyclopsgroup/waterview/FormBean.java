package org.cyclopsgroup.waterview;

/**
 * Flag a Java Bean as form data
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface FormBean
{
    /**
     * @return True if validation is enforced
     */
    boolean strict() default false;
}
