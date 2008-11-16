package org.cyclopsgroup.waterview;

/**
 * Flag an object as non-displayable action
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface Action
{
    /**
     * @return Path this action is mapped to
     */
    String path();

    /**
     * @return Path to redirect to after action is done
     */
    String redirectTo() default "";
}
