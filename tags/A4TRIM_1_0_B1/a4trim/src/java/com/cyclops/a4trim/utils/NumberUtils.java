/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.utils;
import org.apache.commons.lang.StringUtils;
/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class NumberUtils {
    private static final int HEX = 16;
    private static final String HEX_CHARACTERS = "0123456789ABCDEF";
    /** Method toHex() in Class NumberUtils
     * @param value Int value of the number
     * @param size Size of the result string
     * @return String result
     */
    public static final String toHex(int value, int size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = value; i < size && j > 0; i++) {
            int v = j % HEX;
            j /= HEX;
            sb.append(HEX_CHARACTERS.charAt(v));
        }
        sb.append(StringUtils.repeat("0", size));
        return StringUtils.right(StringUtils.reverse(sb.toString()), size);
    }
    private NumberUtils() {
        //do nothing
    }
}
