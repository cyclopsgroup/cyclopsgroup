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
 * extended all references should be to Standard
 */
public abstract class BaseStandard extends BaseObject
{
    /** The Peer class */
    private static final StandardPeer peer =
        new StandardPeer();

        
    /** The value for the standardId field */
    private int standardId;
      
    /** The value for the heightFrom field */
    private int heightFrom;
      
    /** The value for the heightTo field */
    private int heightTo;
      
    /** The value for the weightFrom field */
    private int weightFrom;
      
    /** The value for the weightTo field */
    private int weightTo;
      
    /** The value for the ageFrom field */
    private int ageFrom;
      
    /** The value for the ageTo field */
    private int ageTo;
                                                                
    /** The value for the isFemale field */
    private boolean isFemale = false;
      
    /** The value for the dailyCalorie field */
    private int dailyCalorie;
  
    
    /**
     * Get the StandardId
     *
     * @return int
     */
    public int getStandardId()
    {
        return standardId;
    }

        
    /**
     * Set the value of StandardId
     *
     * @param v new value
     */
    public void setStandardId(int v) 
    {
    
                  if (this.standardId != v)
              {
            this.standardId = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the HeightFrom
     *
     * @return int
     */
    public int getHeightFrom()
    {
        return heightFrom;
    }

        
    /**
     * Set the value of HeightFrom
     *
     * @param v new value
     */
    public void setHeightFrom(int v) 
    {
    
                  if (this.heightFrom != v)
              {
            this.heightFrom = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the HeightTo
     *
     * @return int
     */
    public int getHeightTo()
    {
        return heightTo;
    }

        
    /**
     * Set the value of HeightTo
     *
     * @param v new value
     */
    public void setHeightTo(int v) 
    {
    
                  if (this.heightTo != v)
              {
            this.heightTo = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the WeightFrom
     *
     * @return int
     */
    public int getWeightFrom()
    {
        return weightFrom;
    }

        
    /**
     * Set the value of WeightFrom
     *
     * @param v new value
     */
    public void setWeightFrom(int v) 
    {
    
                  if (this.weightFrom != v)
              {
            this.weightFrom = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the WeightTo
     *
     * @return int
     */
    public int getWeightTo()
    {
        return weightTo;
    }

        
    /**
     * Set the value of WeightTo
     *
     * @param v new value
     */
    public void setWeightTo(int v) 
    {
    
                  if (this.weightTo != v)
              {
            this.weightTo = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the AgeFrom
     *
     * @return int
     */
    public int getAgeFrom()
    {
        return ageFrom;
    }

        
    /**
     * Set the value of AgeFrom
     *
     * @param v new value
     */
    public void setAgeFrom(int v) 
    {
    
                  if (this.ageFrom != v)
              {
            this.ageFrom = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the AgeTo
     *
     * @return int
     */
    public int getAgeTo()
    {
        return ageTo;
    }

        
    /**
     * Set the value of AgeTo
     *
     * @param v new value
     */
    public void setAgeTo(int v) 
    {
    
                  if (this.ageTo != v)
              {
            this.ageTo = v;
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
     * Get the DailyCalorie
     *
     * @return int
     */
    public int getDailyCalorie()
    {
        return dailyCalorie;
    }

        
    /**
     * Set the value of DailyCalorie
     *
     * @param v new value
     */
    public void setDailyCalorie(int v) 
    {
    
                  if (this.dailyCalorie != v)
              {
            this.dailyCalorie = v;
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
                StandardPeer.doInsert((Standard) this);
                setNew(false);
            }
            else
            {
                StandardPeer.doUpdate((Standard) this);
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
                    StandardPeer
                        .doInsert((Standard) this, con);
                    setNew(false);
                }
                else
                {
                    StandardPeer
                        .doUpdate((Standard) this, con);
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
                    StandardPeer.doInsert((Standard) this, con);
                    setNew(false);
                }
                else
                {
                    StandardPeer.doUpdate((Standard) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  standardId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setStandardId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setStandardId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getStandardId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public Standard copy() throws TorqueException
    {
        return copyInto(new Standard());
    }
  
    protected Standard copyInto(Standard copyObj) throws TorqueException
    {
          copyObj.setStandardId(standardId);
          copyObj.setHeightFrom(heightFrom);
          copyObj.setHeightTo(heightTo);
          copyObj.setWeightFrom(weightFrom);
          copyObj.setWeightTo(weightTo);
          copyObj.setAgeFrom(ageFrom);
          copyObj.setAgeTo(ageTo);
          copyObj.setIsFemale(isFemale);
          copyObj.setDailyCalorie(dailyCalorie);
  
                    copyObj.setStandardId(0);
                                                            
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public StandardPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Standard:\n");
        str.append("StandardId = ")
           .append(getStandardId())
           .append("\n");
        str.append("HeightFrom = ")
           .append(getHeightFrom())
           .append("\n");
        str.append("HeightTo = ")
           .append(getHeightTo())
           .append("\n");
        str.append("WeightFrom = ")
           .append(getWeightFrom())
           .append("\n");
        str.append("WeightTo = ")
           .append(getWeightTo())
           .append("\n");
        str.append("AgeFrom = ")
           .append(getAgeFrom())
           .append("\n");
        str.append("AgeTo = ")
           .append(getAgeTo())
           .append("\n");
        str.append("IsFemale = ")
           .append(getIsFemale())
           .append("\n");
        str.append("DailyCalorie = ")
           .append(getDailyCalorie())
           .append("\n");
        return(str.toString());
    }
}
