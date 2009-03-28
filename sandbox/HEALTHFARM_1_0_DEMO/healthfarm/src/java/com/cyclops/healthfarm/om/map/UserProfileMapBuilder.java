package com.cyclops.healthfarm.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  */
public class UserProfileMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "com.cyclops.healthfarm.om.map.UserProfileMapBuilder";


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

        dbMap.addTable("c_hf_userprofiles");
        TableMap tMap = dbMap.getTable("c_hf_userprofiles");

        tMap.setPrimaryKeyMethod(TableMap.ID_BROKER);

        tMap.setPrimaryKeyMethodInfo(tMap.getName());

              tMap.addPrimaryKey("c_hf_userprofiles.PROFILE_ID", new Integer(0));
                    tMap.addColumn("c_hf_userprofiles.USER_ID", new Integer(0));
                    tMap.addColumn("c_hf_userprofiles.BIRTH_YEAR", new Integer(0));
                    tMap.addColumn("c_hf_userprofiles.WEIGHT", new Integer(0));
                    tMap.addColumn("c_hf_userprofiles.HEIGHT", new Integer(0));
                    tMap.addColumn("c_hf_userprofiles.IS_FEMALE", new Boolean(true));
          }
}
