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
 * extended all references should be to ProductCategory
 */
public abstract class BaseProductCategory extends BaseObject
{
    /** The Peer class */
    private static final ProductCategoryPeer peer =
        new ProductCategoryPeer();

        
    /** The value for the id field */
    private int id;
      
    /** The value for the productId field */
    private int productId;
      
    /** The value for the categoryId field */
    private int categoryId;
      
    /** The value for the categoryPath field */
    private String categoryPath;
  
    
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
     * Get the ProductId
     *
     * @return int
     */
    public int getProductId()
    {
        return productId;
    }

        
    /**
     * Set the value of ProductId
     *
     * @param v new value
     */
    public void setProductId(int v) 
    {
    
                  if (this.productId != v)
              {
            this.productId = v;
            setModified(true);
        }
    
        }
  
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
                ProductCategoryPeer.doInsert((ProductCategory) this);
                setNew(false);
            }
            else
            {
                ProductCategoryPeer.doUpdate((ProductCategory) this);
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
                    ProductCategoryPeer
                        .doInsert((ProductCategory) this, con);
                    setNew(false);
                }
                else
                {
                    ProductCategoryPeer
                        .doUpdate((ProductCategory) this, con);
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
                    ProductCategoryPeer.doInsert((ProductCategory) this, con);
                    setNew(false);
                }
                else
                {
                    ProductCategoryPeer.doUpdate((ProductCategory) this, con);
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
      public ProductCategory copy() throws TorqueException
    {
        return copyInto(new ProductCategory());
    }
  
    protected ProductCategory copyInto(ProductCategory copyObj) throws TorqueException
    {
          copyObj.setId(id);
          copyObj.setProductId(productId);
          copyObj.setCategoryId(categoryId);
          copyObj.setCategoryPath(categoryPath);
  
                    copyObj.setId(0);
                              
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ProductCategoryPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("ProductCategory:\n");
        str.append("Id = ")
           .append(getId())
           .append("\n");
        str.append("ProductId = ")
           .append(getProductId())
           .append("\n");
        str.append("CategoryId = ")
           .append(getCategoryId())
           .append("\n");
        str.append("CategoryPath = ")
           .append(getCategoryPath())
           .append("\n");
        return(str.toString());
    }
}
