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
 * extended all references should be to Acl
 */
public abstract class BaseAcl extends BaseObject
{
    /** The Peer class */
    private static final AclPeer peer =
        new AclPeer();

        
    /** The value for the aclId field */
    private int aclId;
      
    /** The value for the ownerName field */
    private String ownerName;
      
    /** The value for the ownerType field */
    private String ownerType;
                                                                
    /** The value for the isRole field */
    private boolean isRole = false;
      
    /** The value for the permission field */
    private String permission;
  
    
    /**
     * Get the AclId
     *
     * @return int
     */
    public int getAclId()
    {
        return aclId;
    }

        
    /**
     * Set the value of AclId
     *
     * @param v new value
     */
    public void setAclId(int v) 
    {
    
                  if (this.aclId != v)
              {
            this.aclId = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the OwnerName
     *
     * @return String
     */
    public String getOwnerName()
    {
        return ownerName;
    }

        
    /**
     * Set the value of OwnerName
     *
     * @param v new value
     */
    public void setOwnerName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.ownerName, v))
              {
            this.ownerName = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the OwnerType
     *
     * @return String
     */
    public String getOwnerType()
    {
        return ownerType;
    }

        
    /**
     * Set the value of OwnerType
     *
     * @param v new value
     */
    public void setOwnerType(String v) 
    {
    
                  if (!ObjectUtils.equals(this.ownerType, v))
              {
            this.ownerType = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the IsRole
     *
     * @return boolean
     */
    public boolean getIsRole()
    {
        return isRole;
    }

        
    /**
     * Set the value of IsRole
     *
     * @param v new value
     */
    public void setIsRole(boolean v) 
    {
    
                  if (this.isRole != v)
              {
            this.isRole = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Permission
     *
     * @return String
     */
    public String getPermission()
    {
        return permission;
    }

        
    /**
     * Set the value of Permission
     *
     * @param v new value
     */
    public void setPermission(String v) 
    {
    
                  if (!ObjectUtils.equals(this.permission, v))
              {
            this.permission = v;
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
                AclPeer.doInsert((Acl) this);
                setNew(false);
            }
            else
            {
                AclPeer.doUpdate((Acl) this);
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
                    AclPeer
                        .doInsert((Acl) this, con);
                    setNew(false);
                }
                else
                {
                    AclPeer
                        .doUpdate((Acl) this, con);
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
                    AclPeer.doInsert((Acl) this, con);
                    setNew(false);
                }
                else
                {
                    AclPeer.doUpdate((Acl) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  aclId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setAclId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setAclId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getAclId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public Acl copy() throws TorqueException
    {
        return copyInto(new Acl());
    }
  
    protected Acl copyInto(Acl copyObj) throws TorqueException
    {
          copyObj.setAclId(aclId);
          copyObj.setOwnerName(ownerName);
          copyObj.setOwnerType(ownerType);
          copyObj.setIsRole(isRole);
          copyObj.setPermission(permission);
  
                    copyObj.setAclId(0);
                                    
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public AclPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Acl:\n");
        str.append("AclId = ")
           .append(getAclId())
           .append("\n");
        str.append("OwnerName = ")
           .append(getOwnerName())
           .append("\n");
        str.append("OwnerType = ")
           .append(getOwnerType())
           .append("\n");
        str.append("IsRole = ")
           .append(getIsRole())
           .append("\n");
        str.append("Permission = ")
           .append(getPermission())
           .append("\n");
        return(str.toString());
    }
}
