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
 * extended all references should be to UserObject
 */
public abstract class BaseUserObject extends BaseObject
{
    /** The Peer class */
    private static final UserObjectPeer peer =
        new UserObjectPeer();

        
    /** The value for the id field */
    private int id;
      
    /** The value for the userId field */
    private int userId;
      
    /** The value for the objectKey field */
    private String objectKey;
      
    /** The value for the objectClassName field */
    private String objectClassName;
      
    /** The value for the objectData field */
    private String objectData;
  
    
    /**
     * Get the Id
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

        
    /**
     * Set the value of Id
     *
     * @param v new value
     */
    public void setId(int v) 
    {
    
                  if (this.id != v)
              {
            this.id = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the UserId
     *
     * @return int
     */
    public int getUserId()
    {
        return userId;
    }

        
    /**
     * Set the value of UserId
     *
     * @param v new value
     */
    public void setUserId(int v) 
    {
    
                  if (this.userId != v)
              {
            this.userId = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ObjectKey
     *
     * @return String
     */
    public String getObjectKey()
    {
        return objectKey;
    }

        
    /**
     * Set the value of ObjectKey
     *
     * @param v new value
     */
    public void setObjectKey(String v) 
    {
    
                  if (!ObjectUtils.equals(this.objectKey, v))
              {
            this.objectKey = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ObjectClassName
     *
     * @return String
     */
    public String getObjectClassName()
    {
        return objectClassName;
    }

        
    /**
     * Set the value of ObjectClassName
     *
     * @param v new value
     */
    public void setObjectClassName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.objectClassName, v))
              {
            this.objectClassName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ObjectData
     *
     * @return String
     */
    public String getObjectData()
    {
        return objectData;
    }

        
    /**
     * Set the value of ObjectData
     *
     * @param v new value
     */
    public void setObjectData(String v) 
    {
    
                  if (!ObjectUtils.equals(this.objectData, v))
              {
            this.objectData = v;
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
                UserObjectPeer.doInsert((UserObject) this);
                setNew(false);
            }
            else
            {
                UserObjectPeer.doUpdate((UserObject) this);
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
                    UserObjectPeer
                        .doInsert((UserObject) this, con);
                    setNew(false);
                }
                else
                {
                    UserObjectPeer
                        .doUpdate((UserObject) this, con);
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
                    UserObjectPeer.doInsert((UserObject) this, con);
                    setNew(false);
                }
                else
                {
                    UserObjectPeer.doUpdate((UserObject) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public UserObject copy() throws TorqueException
    {
        return copyInto(new UserObject());
    }
  
    protected UserObject copyInto(UserObject copyObj) throws TorqueException
    {
          copyObj.setId(id);
          copyObj.setUserId(userId);
          copyObj.setObjectKey(objectKey);
          copyObj.setObjectClassName(objectClassName);
          copyObj.setObjectData(objectData);
  
                    copyObj.setId(0);
                                    
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UserObjectPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("UserObject:\n");
        str.append("Id = ")
           .append(getId())
           .append("\n");
        str.append("UserId = ")
           .append(getUserId())
           .append("\n");
        str.append("ObjectKey = ")
           .append(getObjectKey())
           .append("\n");
        str.append("ObjectClassName = ")
           .append(getObjectClassName())
           .append("\n");
        str.append("ObjectData = ")
           .append(getObjectData())
           .append("\n");
        return(str.toString());
    }
}
