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
 * extended all references should be to User
 */
public abstract class BaseUser extends BaseObject
{
    /** The Peer class */
    private static final UserPeer peer =
        new UserPeer();

        
    /** The value for the id field */
    private int id;
      
    /** The value for the userName field */
    private String userName;
      
    /** The value for the encryptedPassword field */
    private String encryptedPassword;
      
    /** The value for the description field */
    private String description;
                                                                
    /** The value for the isSystem field */
    private boolean isSystem = false;
      
    /** The value for the createdTime field */
    private long createdTime;
                                                                
    /** The value for the isDisabled field */
    private boolean isDisabled = false;
  
    
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
     * Get the UserName
     *
     * @return String
     */
    public String getUserName()
    {
        return userName;
    }

        
    /**
     * Set the value of UserName
     *
     * @param v new value
     */
    public void setUserName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.userName, v))
              {
            this.userName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the EncryptedPassword
     *
     * @return String
     */
    public String getEncryptedPassword()
    {
        return encryptedPassword;
    }

        
    /**
     * Set the value of EncryptedPassword
     *
     * @param v new value
     */
    public void setEncryptedPassword(String v) 
    {
    
                  if (!ObjectUtils.equals(this.encryptedPassword, v))
              {
            this.encryptedPassword = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Description
     *
     * @return String
     */
    public String getDescription()
    {
        return description;
    }

        
    /**
     * Set the value of Description
     *
     * @param v new value
     */
    public void setDescription(String v) 
    {
    
                  if (!ObjectUtils.equals(this.description, v))
              {
            this.description = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the IsSystem
     *
     * @return boolean
     */
    public boolean getIsSystem()
    {
        return isSystem;
    }

        
    /**
     * Set the value of IsSystem
     *
     * @param v new value
     */
    public void setIsSystem(boolean v) 
    {
    
                  if (this.isSystem != v)
              {
            this.isSystem = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the CreatedTime
     *
     * @return long
     */
    public long getCreatedTime()
    {
        return createdTime;
    }

        
    /**
     * Set the value of CreatedTime
     *
     * @param v new value
     */
    public void setCreatedTime(long v) 
    {
    
                  if (this.createdTime != v)
              {
            this.createdTime = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the IsDisabled
     *
     * @return boolean
     */
    public boolean getIsDisabled()
    {
        return isDisabled;
    }

        
    /**
     * Set the value of IsDisabled
     *
     * @param v new value
     */
    public void setIsDisabled(boolean v) 
    {
    
                  if (this.isDisabled != v)
              {
            this.isDisabled = v;
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
                UserPeer.doInsert((User) this);
                setNew(false);
            }
            else
            {
                UserPeer.doUpdate((User) this);
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
                    UserPeer
                        .doInsert((User) this, con);
                    setNew(false);
                }
                else
                {
                    UserPeer
                        .doUpdate((User) this, con);
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
                    UserPeer.doInsert((User) this, con);
                    setNew(false);
                }
                else
                {
                    UserPeer.doUpdate((User) this, con);
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
      public User copy() throws TorqueException
    {
        return copyInto(new User());
    }
  
    protected User copyInto(User copyObj) throws TorqueException
    {
          copyObj.setId(id);
          copyObj.setUserName(userName);
          copyObj.setEncryptedPassword(encryptedPassword);
          copyObj.setDescription(description);
          copyObj.setIsSystem(isSystem);
          copyObj.setCreatedTime(createdTime);
          copyObj.setIsDisabled(isDisabled);
  
                    copyObj.setId(0);
                                                
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UserPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("User:\n");
        str.append("Id = ")
           .append(getId())
           .append("\n");
        str.append("UserName = ")
           .append(getUserName())
           .append("\n");
        str.append("EncryptedPassword = ")
           .append(getEncryptedPassword())
           .append("\n");
        str.append("Description = ")
           .append(getDescription())
           .append("\n");
        str.append("IsSystem = ")
           .append(getIsSystem())
           .append("\n");
        str.append("CreatedTime = ")
           .append(getCreatedTime())
           .append("\n");
        str.append("IsDisabled = ")
           .append(getIsDisabled())
           .append("\n");
        return(str.toString());
    }
}
