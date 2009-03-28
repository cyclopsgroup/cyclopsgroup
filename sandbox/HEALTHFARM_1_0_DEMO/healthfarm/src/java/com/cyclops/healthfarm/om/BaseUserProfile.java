package com.cyclops.healthfarm.om;


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
 * extended all references should be to UserProfile
 */
public abstract class BaseUserProfile extends BaseObject
{
    /** The Peer class */
    private static final UserProfilePeer peer =
        new UserProfilePeer();

        
    /** The value for the profileId field */
    private int profileId;
      
    /** The value for the userId field */
    private int userId;
      
    /** The value for the birthYear field */
    private int birthYear;
      
    /** The value for the weight field */
    private int weight;
      
    /** The value for the height field */
    private int height;
                                                                
    /** The value for the isFemale field */
    private boolean isFemale = false;
  
    
    /**
     * Get the ProfileId
     *
     * @return int
     */
    public int getProfileId()
    {
        return profileId;
    }

        
    /**
     * Set the value of ProfileId
     *
     * @param v new value
     */
    public void setProfileId(int v) 
    {
    
                  if (this.profileId != v)
              {
            this.profileId = v;
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
     * Get the BirthYear
     *
     * @return int
     */
    public int getBirthYear()
    {
        return birthYear;
    }

        
    /**
     * Set the value of BirthYear
     *
     * @param v new value
     */
    public void setBirthYear(int v) 
    {
    
                  if (this.birthYear != v)
              {
            this.birthYear = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Weight
     *
     * @return int
     */
    public int getWeight()
    {
        return weight;
    }

        
    /**
     * Set the value of Weight
     *
     * @param v new value
     */
    public void setWeight(int v) 
    {
    
                  if (this.weight != v)
              {
            this.weight = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Height
     *
     * @return int
     */
    public int getHeight()
    {
        return height;
    }

        
    /**
     * Set the value of Height
     *
     * @param v new value
     */
    public void setHeight(int v) 
    {
    
                  if (this.height != v)
              {
            this.height = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the IsFemale
     *
     * @return boolean
     */
    public boolean getIsFemale()
    {
        return isFemale;
    }

        
    /**
     * Set the value of IsFemale
     *
     * @param v new value
     */
    public void setIsFemale(boolean v) 
    {
    
                  if (this.isFemale != v)
              {
            this.isFemale = v;
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
                UserProfilePeer.doInsert((UserProfile) this);
                setNew(false);
            }
            else
            {
                UserProfilePeer.doUpdate((UserProfile) this);
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
                    UserProfilePeer
                        .doInsert((UserProfile) this, con);
                    setNew(false);
                }
                else
                {
                    UserProfilePeer
                        .doUpdate((UserProfile) this, con);
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
                    UserProfilePeer.doInsert((UserProfile) this, con);
                    setNew(false);
                }
                else
                {
                    UserProfilePeer.doUpdate((UserProfile) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  profileId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setProfileId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setProfileId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getProfileId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public UserProfile copy() throws TorqueException
    {
        return copyInto(new UserProfile());
    }
  
    protected UserProfile copyInto(UserProfile copyObj) throws TorqueException
    {
          copyObj.setProfileId(profileId);
          copyObj.setUserId(userId);
          copyObj.setBirthYear(birthYear);
          copyObj.setWeight(weight);
          copyObj.setHeight(height);
          copyObj.setIsFemale(isFemale);
  
                    copyObj.setProfileId(0);
                                          
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UserProfilePeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("UserProfile:\n");
        str.append("ProfileId = ")
           .append(getProfileId())
           .append("\n");
        str.append("UserId = ")
           .append(getUserId())
           .append("\n");
        str.append("BirthYear = ")
           .append(getBirthYear())
           .append("\n");
        str.append("Weight = ")
           .append(getWeight())
           .append("\n");
        str.append("Height = ")
           .append(getHeight())
           .append("\n");
        str.append("IsFemale = ")
           .append(getIsFemale())
           .append("\n");
        return(str.toString());
    }
}
