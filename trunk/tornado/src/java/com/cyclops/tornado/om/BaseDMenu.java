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
 * extended all references should be to DMenu
 */
public abstract class BaseDMenu extends BaseObject
{
    /** The Peer class */
    private static final DMenuPeer peer =
        new DMenuPeer();

        
    /** The value for the menuId field */
    private int menuId;
                                          
    /** The value for the parentId field */
    private int parentId = -1;
      
    /** The value for the menuName field */
    private String menuName;
      
    /** The value for the description field */
    private String description;
      
    /** The value for the href field */
    private String href;
  
    
    /**
     * Get the MenuId
     *
     * @return int
     */
    public int getMenuId()
    {
        return menuId;
    }

        
    /**
     * Set the value of MenuId
     *
     * @param v new value
     */
    public void setMenuId(int v) 
    {
    
                  if (this.menuId != v)
              {
            this.menuId = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ParentId
     *
     * @return int
     */
    public int getParentId()
    {
        return parentId;
    }

        
    /**
     * Set the value of ParentId
     *
     * @param v new value
     */
    public void setParentId(int v) 
    {
    
                  if (this.parentId != v)
              {
            this.parentId = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the MenuName
     *
     * @return String
     */
    public String getMenuName()
    {
        return menuName;
    }

        
    /**
     * Set the value of MenuName
     *
     * @param v new value
     */
    public void setMenuName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.menuName, v))
              {
            this.menuName = v;
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
     * Get the Href
     *
     * @return String
     */
    public String getHref()
    {
        return href;
    }

        
    /**
     * Set the value of Href
     *
     * @param v new value
     */
    public void setHref(String v) 
    {
    
                  if (!ObjectUtils.equals(this.href, v))
              {
            this.href = v;
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
                DMenuPeer.doInsert((DMenu) this);
                setNew(false);
            }
            else
            {
                DMenuPeer.doUpdate((DMenu) this);
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
                    DMenuPeer
                        .doInsert((DMenu) this, con);
                    setNew(false);
                }
                else
                {
                    DMenuPeer
                        .doUpdate((DMenu) this, con);
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
                    DMenuPeer.doInsert((DMenu) this, con);
                    setNew(false);
                }
                else
                {
                    DMenuPeer.doUpdate((DMenu) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  menuId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setMenuId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setMenuId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getMenuId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public DMenu copy() throws TorqueException
    {
        return copyInto(new DMenu());
    }
  
    protected DMenu copyInto(DMenu copyObj) throws TorqueException
    {
          copyObj.setMenuId(menuId);
          copyObj.setParentId(parentId);
          copyObj.setMenuName(menuName);
          copyObj.setDescription(description);
          copyObj.setHref(href);
  
                    copyObj.setMenuId(0);
                                    
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public DMenuPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("DMenu:\n");
        str.append("MenuId = ")
           .append(getMenuId())
           .append("\n");
        str.append("ParentId = ")
           .append(getParentId())
           .append("\n");
        str.append("MenuName = ")
           .append(getMenuName())
           .append("\n");
        str.append("Description = ")
           .append(getDescription())
           .append("\n");
        str.append("Href = ")
           .append(getHref())
           .append("\n");
        return(str.toString());
    }
}
