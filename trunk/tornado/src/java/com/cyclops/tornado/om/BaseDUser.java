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
 * extended all references should be to DUser
 */
public abstract class BaseDUser extends BaseObject
{
    /** The Peer class */
    private static final DUserPeer peer =
        new DUserPeer();

        
    /** The value for the userId field */
    private int userId;
      
    /** The value for the userName field */
    private String userName;
      
    /** The value for the encryptedPassword field */
    private String encryptedPassword;
      
    /** The value for the description field */
    private String description;
                                                                
    /** The value for the isSystem field */
    private boolean isSystem = false;
      
    /** The value for the email field */
    private String email;
      
    /** The value for the firstName field */
    private String firstName;
      
    /** The value for the middleName field */
    private String middleName;
      
    /** The value for the lastName field */
    private String lastName;
      
    /** The value for the lastSignin field */
    private long lastSignin;
                                          
    /** The value for the signinCounter field */
    private int signinCounter = 0;
      
    /** The value for the lastAccess field */
    private long lastAccess;
      
    /** The value for the createdTime field */
    private long createdTime;
                                                                
    /** The value for the isDisabled field */
    private boolean isDisabled = false;
  
    
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
     * Get the Email
     *
     * @return String
     */
    public String getEmail()
    {
        return email;
    }

        
    /**
     * Set the value of Email
     *
     * @param v new value
     */
    public void setEmail(String v) 
    {
    
                  if (!ObjectUtils.equals(this.email, v))
              {
            this.email = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the FirstName
     *
     * @return String
     */
    public String getFirstName()
    {
        return firstName;
    }

        
    /**
     * Set the value of FirstName
     *
     * @param v new value
     */
    public void setFirstName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.firstName, v))
              {
            this.firstName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the MiddleName
     *
     * @return String
     */
    public String getMiddleName()
    {
        return middleName;
    }

        
    /**
     * Set the value of MiddleName
     *
     * @param v new value
     */
    public void setMiddleName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.middleName, v))
              {
            this.middleName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the LastName
     *
     * @return String
     */
    public String getLastName()
    {
        return lastName;
    }

        
    /**
     * Set the value of LastName
     *
     * @param v new value
     */
    public void setLastName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.lastName, v))
              {
            this.lastName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the LastSignin
     *
     * @return long
     */
    public long getLastSignin()
    {
        return lastSignin;
    }

        
    /**
     * Set the value of LastSignin
     *
     * @param v new value
     */
    public void setLastSignin(long v) 
    {
    
                  if (this.lastSignin != v)
              {
            this.lastSignin = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the SigninCounter
     *
     * @return int
     */
    public int getSigninCounter()
    {
        return signinCounter;
    }

        
    /**
     * Set the value of SigninCounter
     *
     * @param v new value
     */
    public void setSigninCounter(int v) 
    {
    
                  if (this.signinCounter != v)
              {
            this.signinCounter = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the LastAccess
     *
     * @return long
     */
    public long getLastAccess()
    {
        return lastAccess;
    }

        
    /**
     * Set the value of LastAccess
     *
     * @param v new value
     */
    public void setLastAccess(long v) 
    {
    
                  if (this.lastAccess != v)
              {
            this.lastAccess = v;
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
                DUserPeer.doInsert((DUser) this);
                setNew(false);
            }
            else
            {
                DUserPeer.doUpdate((DUser) this);
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
                    DUserPeer
                        .doInsert((DUser) this, con);
                    setNew(false);
                }
                else
                {
                    DUserPeer
                        .doUpdate((DUser) this, con);
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
                    DUserPeer.doInsert((DUser) this, con);
                    setNew(false);
                }
                else
                {
                    DUserPeer.doUpdate((DUser) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  userId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setUserId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setUserId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getUserId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public DUser copy() throws TorqueException
    {
        return copyInto(new DUser());
    }
  
    protected DUser copyInto(DUser copyObj) throws TorqueException
    {
          copyObj.setUserId(userId);
          copyObj.setUserName(userName);
          copyObj.setEncryptedPassword(encryptedPassword);
          copyObj.setDescription(description);
          copyObj.setIsSystem(isSystem);
          copyObj.setEmail(email);
          copyObj.setFirstName(firstName);
          copyObj.setMiddleName(middleName);
          copyObj.setLastName(lastName);
          copyObj.setLastSignin(lastSignin);
          copyObj.setSigninCounter(signinCounter);
          copyObj.setLastAccess(lastAccess);
          copyObj.setCreatedTime(createdTime);
          copyObj.setIsDisabled(isDisabled);
  
                    copyObj.setUserId(0);
                                                                                          
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public DUserPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("DUser:\n");
        str.append("UserId = ")
           .append(getUserId())
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
        str.append("Email = ")
           .append(getEmail())
           .append("\n");
        str.append("FirstName = ")
           .append(getFirstName())
           .append("\n");
        str.append("MiddleName = ")
           .append(getMiddleName())
           .append("\n");
        str.append("LastName = ")
           .append(getLastName())
           .append("\n");
        str.append("LastSignin = ")
           .append(getLastSignin())
           .append("\n");
        str.append("SigninCounter = ")
           .append(getSigninCounter())
           .append("\n");
        str.append("LastAccess = ")
           .append(getLastAccess())
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
