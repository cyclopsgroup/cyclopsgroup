/*
 * Created on 2003-10-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import org.apache.commons.httpclient.Base64;
/**
 * @author joeblack
 * @since 2003-10-6 13:56:39
 */
public class Utils {
    public static void main(String[] args) {
        System.out.println(new String(Base64.encode("joel".getBytes())));
    }
}
