package com.cyclops.a4trim.om;


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
 * extended all references should be to Category
 */
public abstract class BaseCategory extends BaseObject
{
    /** The Peer class */
    private static final CategoryPeer peer =
        new CategoryPeer();

        
    /** The value for the categoryId field */
    private int categoryId;
                                          
    /** The value for the parentId field */
    private int parentId = -1;
      
    /** The value for the categoryPath field */
    private String categoryPath;
      
    /** The value for the categoryCode field */
    private String categoryCode;
                                          
    /** The value for the depth field */
    private int depth = 0;
      
    /** The value for the description field */
    private String description;
      
    /** The value for the thumbnailUri field */
    private String thumbnailUri;
                                                                
    /** The value for the thumbnailAvailable field */
    private boolean thumbnailAvailable = false;
  
    
    /**
     * Get the CategoryId
     *
     * @return int
     */
    public int getCategoryId()
    {
        return categoryId;
    }

        
    /**
     * Set the value of CategoryId
     *
     * @param v new value
     */
    public void setCategoryId(int v) 
    {
    
                  if (this.categoryId != v)
              {
            this.categoryId = v;
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
     * Get the CategoryPath
     *
     * @return String
     */
    public String getCategoryPath()
    {
        return categoryPath;
    }

        
    /**
     * Set the value of CategoryPath
     *
     * @param v new value
     */
    public void setCategoryPath(String v) 
    {
    
                  if (!ObjectUtils.equals(this.categoryPath, v))
              {
            this.categoryPath = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the CategoryCode
     *
     * @return String
     */
    public String getCategoryCode()
    {
        return categoryCode;
    }

        
    /**
     * Set the value of CategoryCode
     *
     * @param v new value
     */
    public void setCategoryCode(String v) 
    {
    
                  if (!ObjectUtils.equals(this.categoryCode, v))
              {
            this.categoryCode = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Depth
     *
     * @return int
     */
    public int getDepth()
    {
        return depth;
    }

        
    /**
     * Set the value of Depth
     *
     * @param v new value
     */
    public void setDepth(int v) 
    {
    
                  if (this.depth != v)
              {
            this.depth = v;
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
     * Get the ThumbnailUri
     *
     * @return String
     */
    public String getThumbnailUri()
    {
        return thumbnailUri;
    }

        
    /**
     * Set the value of ThumbnailUri
     *
     * @param v new value
     */
    public void setThumbnailUri(String v) 
    {
    
                  if (!ObjectUtils.equals(this.thumbnailUri, v))
              {
            this.thumbnailUri = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ThumbnailAvailable
     *
     * @return boolean
     */
    public boolean getThumbnailAvailable()
    {
        return thumbnailAvailable;
    }

        
    /**
     * Set the value of ThumbnailAvailable
     *
     * @param v new value
     */
    public void setThumbnailAvailable(boolean v) 
    {
    
                  if (this.thumbnailAvailable != v)
              {
            this.thumbnailAvailable = v;
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
                CategoryPeer.doInsert((Category) this);
                setNew(false);
            }
            else
            {
                CategoryPeer.doUpdate((Category) this);
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
                    CategoryPeer
                        .doInsert((Category) this, con);
                    setNew(false);
                }
                else
                {
                    CategoryPeer
                        .doUpdate((Category) this, con);
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
                    CategoryPeer.doInsert((Category) this, con);
                    setNew(false);
                }
                else
                {
                    CategoryPeer.doUpdate((Category) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  categoryId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setCategoryId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setCategoryId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getCategoryId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public Category copy() throws TorqueException
    {
        return copyInto(new Category());
    }
  
    protected Category copyInto(Category copyObj) throws TorqueException
    {
          copyObj.setCategoryId(categoryId);
          copyObj.setParentId(parentId);
          copyObj.setCategoryPath(categoryPath);
          copyObj.setCategoryCode(categoryCode);
          copyObj.setDepth(depth);
          copyObj.setDescription(description);
          copyObj.setThumbnailUri(thumbnailUri);
          copyObj.setThumbnailAvailable(thumbnailAvailable);
  
                    copyObj.setCategoryId(0);
                                                      
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public CategoryPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Category:\n");
        str.append("CategoryId = ")
           .append(getCategoryId())
           .append("\n");
        str.append("ParentId = ")
           .append(getParentId())
           .append("\n");
        str.append("CategoryPath = ")
           .append(getCategoryPath())
           .append("\n");
        str.append("CategoryCode = ")
           .append(getCategoryCode())
           .append("\n");
        str.append("Depth = ")
           .append(getDepth())
           .append("\n");
        str.append("Description = ")
           .append(getDescription())
           .append("\n");
        str.append("ThumbnailUri = ")
           .append(getThumbnailUri())
           .append("\n");
        str.append("ThumbnailAvailable = ")
           .append(getThumbnailAvailable())
           .append("\n");
        return(str.toString());
    }
}
