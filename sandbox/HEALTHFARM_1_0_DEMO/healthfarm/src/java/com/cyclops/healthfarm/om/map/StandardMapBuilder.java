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
public class StandardMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "com.cyclops.healthfarm.om.map.StandardMapBuilder";


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

        dbMap.addTable("c_hf_standards");
        TableMap tMap = dbMap.getTable("c_hf_standards");

        tMap.setPrimaryKeyMethod(TableMap.ID_BROKER);

        tMap.setPrimaryKeyMethodInfo(tMap.getName());

              tMap.addPrimaryKey("c_hf_standards.STANDARD_ID", new Integer(0));
                    tMap.addColumn("c_hf_standards.HEIGHT_FROM", new Integer(0));
                    tMap.addColumn("c_hf_standards.HEIGHT_TO", new Integer(0));
                    tMap.addColumn("c_hf_standards.WEIGHT_FROM", new Integer(0));
                    tMap.addColumn("c_hf_standards.WEIGHT_TO", new Integer(0));
                    tMap.addColumn("c_hf_standards.AGE_FROM", new Integer(0));
                    tMap.addColumn("c_hf_standards.AGE_TO", new Integer(0));
                    tMap.addColumn("c_hf_standards.IS_FEMALE", new Boolean(true));
                    tMap.addColumn("c_hf_standards.DAILY_CALORIE", new Integer(0));
          }
}
