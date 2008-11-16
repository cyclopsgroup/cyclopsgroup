package org.cyclopsgroup.waterview;

/**
 * Flag an object as a page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface Page
{
    /**
     * @return Detail description of this page
     */
    String description() default "";

    /**
     * @return Keywords of this page
     */
    String[] keywords() default {};

    /**
     * @return Path this web page is mapped to
     */
    String path();

    /**
     * @return Title of page
     */
    String title() default "";
}
