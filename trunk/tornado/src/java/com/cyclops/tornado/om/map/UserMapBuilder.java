package com.cyclops.tornado.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  */
public class UserMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "com.cyclops.tornado.om.map.UserMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("default");

        dbMap.addTable("c_tnd_users");
        TableMap tMap = dbMap.getTable("c_tnd_users");

                tMap.setPrimaryKeyMethod(TableMap.ID_BROKER);
        
                tMap.setPrimaryKeyMethodInfo(tMap.getName());
        
                                      tMap.addPrimaryKey("c_tnd_users.USER_ID", new Integer(0));
                                                        tMap.addColumn("c_tnd_users.USER_NAME", new String());
                                                        tMap.addColumn("c_tnd_users.ENCRYPTED_PASSWORD", new String());
                                                        tMap.addColumn("c_tnd_users.DESCRIPTION", new String());
                                                        tMap.addColumn("c_tnd_users.IS_SYSTEM", new Boolean(true));
                                                        tMap.addColumn("c_tnd_users.EMAIL", new String());
                                                        tMap.addColumn("c_tnd_users.FIRST_NAME", new String());
                                                        tMap.addColumn("c_tnd_users.MIDDLE_NAME", new String());
                                                        tMap.addColumn("c_tnd_users.LAST_NAME", new String());
                                                        tMap.addColumn("c_tnd_users.LAST_SIGNIN", new Long(0));
                                                        tMap.addColumn("c_tnd_users.SIGNIN_COUNTER", new Integer(0));
                                                        tMap.addColumn("c_tnd_users.LAST_ACCESS", new Long(0));
                                                        tMap.addColumn("c_tnd_users.CREATED_TIME", new Long(0));
                                                        tMap.addColumn("c_tnd_users.IS_DISABLED", new Boolean(true));
                              }
}
