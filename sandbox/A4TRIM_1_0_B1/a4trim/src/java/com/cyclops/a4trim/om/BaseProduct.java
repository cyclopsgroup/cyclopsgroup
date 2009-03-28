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
 * extended all references should be to Product
 */
public abstract class BaseProduct extends BaseObject
{
    /** The Peer class */
    private static final ProductPeer peer =
        new ProductPeer();

        
    /** The value for the productId field */
    private int productId;
      
    /** The value for the productCode field */
    private String productCode;
      
    /** The value for the description field */
    private String description;
      
    /** The value for the thumbnailUri field */
    private String thumbnailUri;
                                                                
    /** The value for the thumbnailAvailable field */
    private boolean thumbnailAvailable = false;
      
    /** The value for the imageUri field */
    private String imageUri;
                                                                
    /** The value for the imageAvailable field */
    private boolean imageAvailable = false;
      
    /** The value for the attribute1 field */
    private String attribute1;
      
    /** The value for the attribute2 field */
    private String attribute2;
      
    /** The value for the attribute3 field */
    private String attribute3;
      
    /** The value for the attribute4 field */
    private String attribute4;
      
    /** The value for the attribute5 field */
    private String attribute5;
  
    
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
     * Get the ProductCode
     *
     * @return String
     */
    public String getProductCode()
    {
        return productCode;
    }

        
    /**
     * Set the value of ProductCode
     *
     * @param v new value
     */
    public void setProductCode(String v) 
    {
    
                  if (!ObjectUtils.equals(this.productCode, v))
              {
            this.productCode = v;
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
     * Get the ImageUri
     *
     * @return String
     */
    public String getImageUri()
    {
        return imageUri;
    }

        
    /**
     * Set the value of ImageUri
     *
     * @param v new value
     */
    public void setImageUri(String v) 
    {
    
                  if (!ObjectUtils.equals(this.imageUri, v))
              {
            this.imageUri = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the ImageAvailable
     *
     * @return boolean
     */
    public boolean getImageAvailable()
    {
        return imageAvailable;
    }

        
    /**
     * Set the value of ImageAvailable
     *
     * @param v new value
     */
    public void setImageAvailable(boolean v) 
    {
    
                  if (this.imageAvailable != v)
              {
            this.imageAvailable = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Attribute1
     *
     * @return String
     */
    public String getAttribute1()
    {
        return attribute1;
    }

        
    /**
     * Set the value of Attribute1
     *
     * @param v new value
     */
    public void setAttribute1(String v) 
    {
    
                  if (!ObjectUtils.equals(this.attribute1, v))
              {
            this.attribute1 = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Attribute2
     *
     * @return String
     */
    public String getAttribute2()
    {
        return attribute2;
    }

        
    /**
     * Set the value of Attribute2
     *
     * @param v new value
     */
    public void setAttribute2(String v) 
    {
    
                  if (!ObjectUtils.equals(this.attribute2, v))
              {
            this.attribute2 = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Attribute3
     *
     * @return String
     */
    public String getAttribute3()
    {
        return attribute3;
    }

        
    /**
     * Set the value of Attribute3
     *
     * @param v new value
     */
    public void setAttribute3(String v) 
    {
    
                  if (!ObjectUtils.equals(this.attribute3, v))
              {
            this.attribute3 = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Attribute4
     *
     * @return String
     */
    public String getAttribute4()
    {
        return attribute4;
    }

        
    /**
     * Set the value of Attribute4
     *
     * @param v new value
     */
    public void setAttribute4(String v) 
    {
    
                  if (!ObjectUtils.equals(this.attribute4, v))
              {
            this.attribute4 = v;
            setModified(true);
        }
    
        }
  
    /**
     * Get the Attribute5
     *
     * @return String
     */
    public String getAttribute5()
    {
        return attribute5;
    }

        
    /**
     * Set the value of Attribute5
     *
     * @param v new value
     */
    public void setAttribute5(String v) 
    {
    
                  if (!ObjectUtils.equals(this.attribute5, v))
              {
            this.attribute5 = v;
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
                ProductPeer.doInsert((Product) this);
                setNew(false);
            }
            else
            {
                ProductPeer.doUpdate((Product) this);
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
                    ProductPeer
                        .doInsert((Product) this, con);
                    setNew(false);
                }
                else
                {
                    ProductPeer
                        .doUpdate((Product) this, con);
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
                    ProductPeer.doInsert((Product) this, con);
                    setNew(false);
                }
                else
                {
                    ProductPeer.doUpdate((Product) this, con);
                }
            }

        }


          
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  productId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setProductId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setProductId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getProductId());
      }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       */
      public Product copy() throws TorqueException
    {
        return copyInto(new Product());
    }
  
    protected Product copyInto(Product copyObj) throws TorqueException
    {
          copyObj.setProductId(productId);
          copyObj.setProductCode(productCode);
          copyObj.setDescription(description);
          copyObj.setThumbnailUri(thumbnailUri);
          copyObj.setThumbnailAvailable(thumbnailAvailable);
          copyObj.setImageUri(imageUri);
          copyObj.setImageAvailable(imageAvailable);
          copyObj.setAttribute1(attribute1);
          copyObj.setAttribute2(attribute2);
          copyObj.setAttribute3(attribute3);
          copyObj.setAttribute4(attribute4);
          copyObj.setAttribute5(attribute5);
  
                    copyObj.setProductId(0);
                                                                              
  
        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ProductPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Product:\n");
        str.append("ProductId = ")
           .append(getProductId())
           .append("\n");
        str.append("ProductCode = ")
           .append(getProductCode())
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
        str.append("ImageUri = ")
           .append(getImageUri())
           .append("\n");
        str.append("ImageAvailable = ")
           .append(getImageAvailable())
           .append("\n");
        str.append("Attribute1 = ")
           .append(getAttribute1())
           .append("\n");
        str.append("Attribute2 = ")
           .append(getAttribute2())
           .append("\n");
        str.append("Attribute3 = ")
           .append(getAttribute3())
           .append("\n");
        str.append("Attribute4 = ")
           .append(getAttribute4())
           .append("\n");
        str.append("Attribute5 = ")
           .append(getAttribute5())
           .append("\n");
        return(str.toString());
    }
}
