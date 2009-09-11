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
     * @return Max number of characters the field can contain. Default value is -1 which means field length is unlimited
     */
    int maxLength() default -1;

    /**
     * @return Zero based position of field
     */
    int position();
}
