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
 * extended all references should be to DConf
 */
public abstract class BaseDConf extends BaseObject
{
    /** The Peer class */
    private static final DConfPeer peer =
        new DConfPeer();

        
    /** The value for the confId field */
    private int confId;
      
    /** The value for the confKey field */
    private String confKey;
      
    /** The value for the confValue field */
    private String confValue;
  
    
    /**
     * Get the ConfId
     *
     * @return int
     */
    public int getConfId()
    {
        return confId;
    }

        
    /**
     * Set the value of ConfId
     *
     * @param v new value
     */
    public void setConfId(int v) 
    {
    
                  if (this.confId != v)
              {
            this.confId = v;
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
        return confKey;
    }

        
    /**
     * Set the value of ConfKey
     *
     * @param v new value
     */
    public void setConfKey(String v) 
    {
    
                  if (!ObjectUtils.equals(this.confKey, v))
              {
            this.confKey = v;
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
        return confValue;
    }

        
    /**
     * Set the value of ConfValue
     *
     * @param v new value
     */
    public void setConfValue(String v) 
    {
    
                  if (!ObjectUtils.equals(this.confValue, v))
              {
            this.confValue = v;
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
                DConfPeer.doInsert((DConf) this);
                setNew(false);
            }
            else
            {
                DConfPeer.doUpdate((DConf) this);
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
                    DConfPeer
                        .doInsert((DConf) this, con);
                    setNew(false);
                }
                else
                {
                    DConfPeer
                        .doUpdate((DConf) this, con);
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
                    DConfPeer.doInsert((DConf) this, con);
                    setNew(false);
                }
                else
                {
                    DConfPeer.doUpdate((DConf) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  confId ObjectKey
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
      public DConf copy() throws TorqueException
    {
        return copyInto(new DConf());
    }
  
    protected DConf copyInto(DConf copyObj) throws TorqueException
    {
          copyObj.setConfId(confId);
          copyObj.setConfKey(confKey);
          copyObj.setConfValue(confValue);
  
                    copyObj.setConfId(0);
                        
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public DConfPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("DConf:\n");
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
