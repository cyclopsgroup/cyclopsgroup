/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import junit.framework.TestCase;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class PassportTest extends TestCase {
    public void testWorking() {
        Passport passport = new Passport();
        TestAsset ta = new TestAsset("aa:1|2|aaa");
        assertFalse(passport.accept(ta));
        passport.addPermission("aa:1|*|aaa");
        assertTrue(passport.accept(ta));
    }
    private class TestAsset implements Asset {
        private String expression;
        private TestAsset(String expr) {
            expression = expr;
        }
        /** Implementation of method getAssetCode() in this class
         * @see com.cyclops.tornado.Asset#getAssetCode()
         */
        public String getAssetCode() {
            return expression;
        }
    }
}
