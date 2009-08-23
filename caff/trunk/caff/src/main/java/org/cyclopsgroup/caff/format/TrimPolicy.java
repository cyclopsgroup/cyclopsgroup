package org.cyclopsgroup.caff.format;

/**
 * Define what should layout do when value is longer than limit
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum TrimPolicy
{
    /**
     * Default value means, truncate right when align left, or truncate left when align right
     */
    FORWARD,
    /**
     * Truncate left when align left, or truncate right when align right
     */
    REVERSE,
    /**
     * When value is too long, throw {@link IllegalArgumentException} instead of truncating
     */
    DISALLOWED;
}
