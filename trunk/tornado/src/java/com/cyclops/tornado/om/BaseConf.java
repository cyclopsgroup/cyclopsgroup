package com.cyclops.tornado.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * You should not use this class directly.  It should not even be
 * extended all references should be to Conf
 */
public abstract class BaseConf extends BaseObject
{
    /** The Peer class */
    private static final ConfPeer peer =
        new ConfPeer();

                  
        /**
         * The value for the conf_id field
         */
        private int conf_id;
              
        /**
         * The value for the conf_key field
         */
        private String conf_key;
              
        /**
         * The value for the conf_value field
         */
        private String conf_value;
      
      
        /**
         * Get the ConfId
         *
         * @return int
         */
        public int getConfId()
        {
            return conf_id;
        }

                
        /**
         * Set the value of ConfId
         *
         * @param v new value
         */
        public void setConfId(int v) 
        {
          


         if (this.conf_id != v)
        {
             this.conf_id = v;
            setModified(true);
        }

             }


        /**
         * Get the ConfKey
         *
         * @return String
         */
        public String getConfKey()
        {
            return conf_key;
        }

                
        /**
         * Set the value of ConfKey
         *
         * @param v new value
         */
        public void setConfKey(String v) 
        {
          


         if (!ObjectUtils.equals(this.conf_key, v))
        {
             this.conf_key = v;
            setModified(true);
        }

             }


        /**
         * Get the ConfValue
         *
         * @return String
         */
        public String getConfValue()
        {
            return conf_value;
        }

                
        /**
         * Set the value of ConfValue
         *
         * @param v new value
         */
        public void setConfValue(String v) 
        {
          


         if (!ObjectUtils.equals(this.conf_value, v))
        {
             this.conf_value = v;
            setModified(true);
        }

             }


 
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
         if (isModified())
        {
            if (isNew())
            {
                ConfPeer.doInsert((Conf) this);
                setNew(false);
            }
            else
            {
                ConfPeer.doUpdate((Conf) this);
            }
        }
     }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
         if (isModified())
        {
            try
            {
                con = Torque.getConnection(dbName);
                if (isNew())
                {
                    ConfPeer
                        .doInsert((Conf) this, con);
                    setNew(false);
                }
                else
                {
                    ConfPeer
                        .doUpdate((Conf) this, con);
                }
            }
            finally
            {
                Torque.closeConnection(con);
            }
        }

     }

      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
  
        // If this object has been modified, then save it to the database.
        if (isModified())
        {
            if (isNew())
            {
                ConfPeer.doInsert((Conf) this, con);
                setNew(false);
            }
            else
            {
                ConfPeer.doUpdate((Conf) this, con);
            }
        }

       }


    
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  conf_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setConfId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setConfId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getConfId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     */
    public Conf copy() throws TorqueException
    {
        return copyInto(new Conf());
    }

    protected Conf copyInto(Conf copyObj) throws TorqueException
    {
        copyObj.setConfId(conf_id);
        copyObj.setConfKey(conf_key);
        copyObj.setConfValue(conf_value);

                      copyObj.setConfId(0);
                    


        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ConfPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Conf:\n");
              str.append("ConfId = ")
           .append(getConfId())
           .append("\n");
              str.append("ConfKey = ")
           .append(getConfKey())
           .append("\n");
              str.append("ConfValue = ")
           .append(getConfValue())
           .append("\n");
              return(str.toString());
    }
}
