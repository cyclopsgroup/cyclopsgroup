/*
 * Created on 2003-10-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.regexp.RE;
import org.apache.regexp.REUtil;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Passport {
    /** Key in user temp storage */
    public static final String KEY_IN_USER = "passport";
    /**
     * @author jiaqi guo
     * @email g-cyclops@users.sourceforge.net
     */
    public class Permission implements Asset {
        private String expression;
        private String name;
        private String[] values;
        /** The only constructor
         * @param expr permission expression or asset code
         */
        public Permission(String expr) {
            expression = expr;
            String[] parts = StringUtils.split(expression, ":");
            name = parts[0];
            values = StringUtils.split(parts[1], "|");
        }
        /** Implementation of method equals() in this class
         * @see java.lang.Object#equals(java.lang.Object)
         */
        public boolean equals(Object obj) {
            if (obj instanceof Permission) {
                return StringUtils.equals(
                    expression,
                    ((Permission) obj).expression);
            } else {
                return false;
            }
        }
        /** Implementation of method getAssetCode() in this class
         * @see com.cyclops.tornado.Asset#getAssetCode()
         */
        public String getAssetCode() {
            return expression;
        }
        /** Method getName()
         * @return Name of it
         */
        public String getName() {
            return name;
        }
        /** Method getValues()
         * @return Values of it
         */
        public String[] getValues() {
            return values;
        }
        /** Implementation of method hashCode() in this class
         * @see java.lang.Object#hashCode()
         */
        public int hashCode() {
            return expression.hashCode();
        }
        /** Method match()
         * @param permission Permission to be matched
         * @return If matched
         */
        public boolean match(Permission permission) {
            if (StringUtils.equals(name, permission.name)
                && values.length == permission.values.length) {
                try {
                    RE re = REUtil.createRE("<" + permission.expression + ">");
                    return re.match("<" + expression + ">");
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
    private MultiHashMap repo = new MultiHashMap();
    /** Method accept()
     * @param asset Asset to be tested
     * @return If it is accepted
     */
    public boolean accept(Asset asset) {
        Permission assetPermission = new Permission(asset.getAssetCode());
        if (repo.containsKey(assetPermission.getName())) {
            Collection perms = (Collection) repo.get(assetPermission.getName());
            for (Iterator i = perms.iterator(); i.hasNext();) {
                Permission p = (Permission) i.next();
                if (assetPermission.match(p)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    /** Method addPermission()
     * @param expression Permission expression
     */
    public void addPermission(String expression) {
        Permission p = new Permission(expression);
        if (!accept(p)) {
            Collection c = (Collection) repo.get(p.getName());
            if (c != null) {
                ArrayList tobeRemoved = new ArrayList();
                for (Iterator i = c.iterator(); i.hasNext();) {
                    Permission perm = (Permission) i.next();
                    if (perm.match(p)) {
                        tobeRemoved.add(perm);
                    }
                }
                for (Iterator i = tobeRemoved.iterator(); i.hasNext();) {
                    Permission perm = (Permission) i.next();
                    repo.remove(perm.getName(), perm);
                }
            }
            repo.put(p.getName(), p);
        }
    }
}
