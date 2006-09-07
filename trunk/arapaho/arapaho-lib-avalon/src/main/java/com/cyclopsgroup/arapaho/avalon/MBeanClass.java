/**
 * 
 */
package com.cyclopsgroup.arapaho.avalon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiaqi
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MBeanClass {
    /**
     * @return Description of the mbean class
     */
    String value();
}
