/*
 * Created on 2003-9-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.om;

/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductTest extends OMTestCase {

    /** Method testSetDescription() in Class ProductTest */
    public void testSetDescription() {
        Product p = new Product();
        assertTrue(p.isNew());
        p.setDescription("aaaa");
        assertTrue(p.isModified());
    }
}
