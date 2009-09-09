package org.cyclopsgroup.caff.format;

/**
 * Flag field as a CSV field
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface CSVField
{
    /**
     * @return Always wrap value with double quots
     */
    boolean alwaysQuote() default false;

    /**
     * @return Zero based position of field
     */
    int position();
}
