package com.cyclops.tornado.om;
import org.apache.torque.om.Persistent;
/**
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 */
public class Acl extends BaseAcl implements Persistent {
    public static final String OWNER_TYPE_USER = "U";
    public static final String OWNER_TYPE_GROUP = "G";
    public static final String OWNER_TYPE_ROLE = "R";
}
