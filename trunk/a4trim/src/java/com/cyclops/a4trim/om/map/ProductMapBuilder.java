package com.cyclops.a4trim.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  */
public class ProductMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "com.cyclops.a4trim.om.map.ProductMapBuilder";


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

        dbMap.addTable("c_a4trim_products");
        TableMap tMap = dbMap.getTable("c_a4trim_products");

        tMap.setPrimaryKeyMethod(TableMap.ID_BROKER);

        tMap.setPrimaryKeyMethodInfo(tMap.getName());

              tMap.addPrimaryKey("c_a4trim_products.PRODUCT_ID", new Integer(0));
                    tMap.addColumn("c_a4trim_products.PRODUCT_CODE", new String());
                    tMap.addColumn("c_a4trim_products.DESCRIPTION", new String());
                    tMap.addColumn("c_a4trim_products.THUMBNAIL_URI", new String());
                    tMap.addColumn("c_a4trim_products.THUMBNAIL_AVAILABLE", new Boolean(true));
                    tMap.addColumn("c_a4trim_products.IMAGE_URI", new String());
                    tMap.addColumn("c_a4trim_products.IMAGE_AVAILABLE", new Boolean(true));
                    tMap.addColumn("c_a4trim_products.ATTRIBUTE1", new String());
                    tMap.addColumn("c_a4trim_products.ATTRIBUTE2", new String());
                    tMap.addColumn("c_a4trim_products.ATTRIBUTE3", new String());
                    tMap.addColumn("c_a4trim_products.ATTRIBUTE4", new String());
                    tMap.addColumn("c_a4trim_products.ATTRIBUTE5", new String());
          }
}
